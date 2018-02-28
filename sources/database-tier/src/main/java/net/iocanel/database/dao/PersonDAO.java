package net.iocanel.database.dao;

import java.util.List;
import net.iocanel.database.entities.Person;

/**
 *
 * @author iocanel
 */
public interface PersonDAO {

	public void create(Person person) throws Exception;
	public void edit(Person person) throws Exception;
	public void destroy(Integer id) throws Exception;
	public Person findPerson(Integer id);
	public List<Person> findAllPersons();
}
