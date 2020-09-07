package de.gothaer.services;

import de.gothaer.persistence.PersonRepository;
import de.gothaer.persistence.model.Person;

public class PersonServiceImpl {
	
	private final PersonRepository personRepository;

	public PersonServiceImpl(final PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	/**
	 * Exception wenn:
	 * 
	 * 1. personParameter ist null
	 * 2. vorname oder nachname null sind
	 * 3. vorname oder nachname weniger als 2 zeichen haben
	 * 4. vorname Attila 
	 * 5. wenn repo eine RTE auslöst -> PSE
	 * 
	 * Was übrig bleibt ist der Erfolgsfall. Person wird mit save gespeichert
	 * 
	 * @param person
	 * @throws PersonenServiceException
	 */
	public void speichern(Person person) throws PersonenServiceException{
		if(person == null) throw new PersonenServiceException("Parameter darf nicht null sein");
	}

}
