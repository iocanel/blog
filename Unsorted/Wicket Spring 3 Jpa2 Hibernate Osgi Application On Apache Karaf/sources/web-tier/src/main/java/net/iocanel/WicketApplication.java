package net.iocanel;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see net.iocanel.Start#main(String[])
 */
public class WicketApplication extends WebApplication implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	boolean isInitialized = false;

	/**
	 * Constructor
	 */
	public WicketApplication() {
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<PersonPage> getHomePage() {
		return PersonPage.class;
	}

	@Override
	protected void init() {
		// Configure Wicket to add a timestamp to the request for a resource
		getResourceSettings().setAddLastModifiedTimeToResourceReferenceUrl(true);

		if (!isInitialized) {
			super.init();
			setListeners();
			isInitialized = true;
		}
	}

	private void setListeners() {
		addComponentInstantiationListener(new SpringComponentInjector(this, applicationContext, true));
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}
}
