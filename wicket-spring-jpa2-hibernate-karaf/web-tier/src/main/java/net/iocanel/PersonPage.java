package net.iocanel;

import java.util.List;

import net.iocanel.database.dao.PersonDAO;
import net.iocanel.database.entities.Person;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.LoggerFactory;

/**
 * Homepage
 */
public class PersonPage extends WebPage {

	@SpringBean
	private PersonDAO personDAO;

	public PersonPage() {
		//Model Declaration
		final IModel<List<Person>> personListModel = new LoadableDetachableModel<List<Person>>() {

			@Override
			protected List<Person> load() {
				return personDAO.findAllPersons();
			}
		};

		final CompoundPropertyModel<Person> personModel = new CompoundPropertyModel<Person>(new Person());

		//Forms
		final Form personForm = new Form("personForm", personModel);

		//Markup Containers
		final WebMarkupContainer personListContainer = new WebMarkupContainer("personListContainer");
		final WebMarkupContainer personDetailsContainer = new WebMarkupContainer("personDetailsContainer");
		//Components
		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		ListView<Person> personListView = new PropertyListView<Person>("personListView", personListModel) {

			@Override
			protected void populateItem(final ListItem<Person> item) {
				final Person person = item.getModelObject();
				item.add(new Label("firstName"));
				item.add(new Label("lastName"));
				item.add(new AjaxFallbackButton("editPerson", personForm) {

					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
						personForm.setModelObject(person);
						target.addComponent(personDetailsContainer);
					}
				}.setDefaultFormProcessing(false));

				item.add(new AjaxFallbackButton("deletePerson", personForm) {

					@Override
					protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
						try {
							personDAO.destroy(person.getId());
							//personForm.setModelObject(new Person());
						} catch (Exception ex) {
							error("Could not delete person");
						}
						target.addComponent(personListContainer);
						target.addComponent(personDetailsContainer);
						target.addComponent(feedbackPanel);
					}
				}.setDefaultFormProcessing(false));
			}
		};

		personDetailsContainer.add(new RequiredTextField("firstName"));
		personDetailsContainer.add(new RequiredTextField("lastName"));
		personDetailsContainer.add(new AjaxFallbackButton("save", personForm) {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				try {
					Person p = personModel.getObject();
					if (p.getId() != null) {
						personDAO.edit(personModel.getObject());
					} else {
						personDAO.create(personModel.getObject());
					}
					personModel.setObject(new Person());
					target.addComponent(personListContainer);
					target.addComponent(feedbackPanel);
				} catch (Exception ex) {
					error("Could not save person");
				}
				target.addComponent(personListContainer);
				target.addComponent(personDetailsContainer);
				target.addComponent(feedbackPanel);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				super.onError(target, form);
				target.addComponent(feedbackPanel);
			}
		});

		personDetailsContainer.add(new AjaxFallbackButton("cancel", personForm) {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Person person = new Person();
				personForm.setModelObject(person);
				target.addComponent(personDetailsContainer);
				target.addComponent(feedbackPanel);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				super.onError(target, form);
				target.addComponent(feedbackPanel);
			}
		}.setDefaultFormProcessing(false));

		personListContainer.setOutputMarkupId(true);
		personDetailsContainer.setOutputMarkupId(true);
		feedbackPanel.setOutputMarkupId(true);

		//Page Layout
		add(personForm);
		personListContainer.add(personListView);
		personForm.add(personListContainer);
		personForm.add(personDetailsContainer);
		personForm.add(feedbackPanel);
	}
}
