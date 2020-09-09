package de.gothaer.services;

import java.util.List;
import java.util.UUID;

import de.gothaer.persistence.PersonRepository;
import de.gothaer.persistence.model.Person;

public class PersonServiceImpl {
	
	private final PersonRepository personRepository;
	private final List<String> antipathen;

	public PersonServiceImpl(final PersonRepository personRepository, final List<String> antipathen) {
		this.personRepository = personRepository;
		this.antipathen = antipathen;
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
		try {
			trySpeichern(person);
		} catch (RuntimeException e) {
			throw new PersonenServiceException("Fehler in der Datenbank",e);
		}
	}

	private void trySpeichern(Person person) throws PersonenServiceException {
		checkPerson(person);
		person.setId(UUID.randomUUID().toString());
		personRepository.save(person);
	}

	private void checkPerson(Person person) throws PersonenServiceException {
		validatePerson(person);
		businessCheck(person);
	}

	private void businessCheck(Person person) throws PersonenServiceException {
		if(antipathen.contains(person.getVorname())) throw new PersonenServiceException("Antipath");
	}

	private void validatePerson(Person person) throws PersonenServiceException {
		if(person == null) throw new PersonenServiceException("Parameter darf nicht null sein");
		if(person.getVorname() == null || person.getVorname().length() < 2) throw new PersonenServiceException("Vorname zu kurz");
		if(person.getNachname() == null || person.getNachname().length() < 2) throw new PersonenServiceException("Nachname zu kurz");
	}
	
	public List<Person> findAllJohns() {
		return null;
	}

}
