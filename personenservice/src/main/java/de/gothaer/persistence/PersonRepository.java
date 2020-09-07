package de.gothaer.persistence;

import java.util.List;

import de.gothaer.persistence.model.Person;

public interface PersonRepository {
	
	void save(Person person);
	boolean update(Person person);
	boolean remove(Person person);
	
	Person findById(String id);
	List<Person> findAll(); 
	

}
