package net.iocanel.database.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;
import net.iocanel.database.entities.Person;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author iocanel
 */
@Transactional
public class PersonJpaDAO implements PersonDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void create(Person person) throws Exception {
		entityManager.persist(person);
		entityManager.flush();
	}

	public void edit(Person person) throws Exception {
		entityManager.merge(person);
		entityManager.flush();
	}

	public void destroy(Integer id) throws Exception {
		entityManager.remove(findPerson(id));
		entityManager.flush();
	}

	public List<Person> findPersonEntities(int maxResults, int firstResult) {
		return findPersonEntities(false, maxResults, firstResult);
	}

	private List<Person> findPersonEntities(boolean all, int maxResults, int firstResult) {
		Query q = entityManager.createQuery("select object(o) from Person as o");
		if (!all) {
			q.setMaxResults(maxResults);
			q.setFirstResult(firstResult);
		}
		return q.getResultList();
	}

	public Person findPerson(Integer id) {
		return entityManager.find(Person.class, id);
	}

	public int getPersonCount() {
		Query q = entityManager.createQuery("select count(o) from Person as o");
		return ((Long) q.getSingleResult()).intValue();
	}

	public List<Person> findAllPersons() {
		Query q = entityManager.createNamedQuery("Person.findAll");
		return q.getResultList();
	}
}
