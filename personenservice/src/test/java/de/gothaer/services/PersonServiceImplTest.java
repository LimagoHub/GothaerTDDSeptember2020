package de.gothaer.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.gothaer.persistence.PersonRepository;
import de.gothaer.persistence.model.Person;

import static org.mockito.Mockito.*;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {
	@Mock
	private PersonRepository personRepositoryMock;
	@Mock
	private List<String> antipathenMock;
	@InjectMocks
	private PersonServiceImpl objectUnderTest;

	@Test
	public void speichern_parameterIsNull_throwsPersonenServiceException() {
		try {
			objectUnderTest.speichern(null);
			fail("Upps");
		} catch (PersonenServiceException e) {
			assertEquals("Parameter darf nicht null sein", e.getMessage());
		}

	}

	@Test
	public void speichern_firstNameIsNull_throwsPersonenServiceException() {
		try {
			final Person person = new Person(null, "Doe");
			objectUnderTest.speichern(person);
			fail("Upps");
		} catch (PersonenServiceException e) {
			assertEquals("Vorname zu kurz", e.getMessage());
		}

	} 
	
	@Test
	public void speichern_firstNameIsNull_throwsPersonenServiceExceptionvariante2() {
		
			final Person person = new Person(null, "Doe");
			//assertThat(PersonenServiceException.class, ()->objectUnderTest.speichern(person));
	}

	@Test
	public void speichern_firstNameToShort_throwsPersonenServiceException() {
		try {
			final Person person = new Person("J", "Doe");
			objectUnderTest.speichern(person);
			fail("Upps");
		} catch (PersonenServiceException e) {
			assertEquals("Vorname zu kurz", e.getMessage());
		}

	}

	@Test
	public void speichern_lastNameIsNull_throwsPersonenServiceException() {
		try {
			final Person person = new Person("John", null);
			objectUnderTest.speichern(person);
			fail("Upps");
		} catch (PersonenServiceException e) {
			assertEquals("Nachname zu kurz", e.getMessage());
		}

	}

	@Test
	public void speichern_lastNameToShort_throwsPersonenServiceException() {
		try {
			final Person person = new Person("John", "D");
			objectUnderTest.speichern(person);
			fail("Upps");
		} catch (PersonenServiceException e) {
			assertEquals("Nachname zu kurz", e.getMessage()); 
		}

	}

//	@Test
//	public void speichern_Antipath_throwsPersonenServiceException() {
//		try {
//			when(antipathenMock.contains(anyString())).thenReturn(true);
//			final Person person = new Person();
//			objectUnderTest.speichern(person);
//			fail("Upps");
//		} catch (PersonenServiceException e) {
//			assertEquals("Antipath", e.getMessage());
//		}
//
//	}

	@Test
	public void speichern_runtimeExceptionInUnderlyingService_throwsPersonenServiceException() {
		try {
			when(antipathenMock.contains(anyString())).thenReturn(false);
			doThrow(new ArrayIndexOutOfBoundsException()).when(personRepositoryMock).save((Person)anyObject());
			final Person validPerson = new Person("John","Doe");
			objectUnderTest.speichern(validPerson);
			fail("Upps");
		} catch (PersonenServiceException e) {
			assertEquals("Fehler in der Datenbank", e.getMessage());
		}

	}

	@Test
	public void speichern_HappyDay_PersonSavedInRepository() throws Exception {
		when(antipathenMock.contains(anyString())).thenReturn(false);
		final Person validPerson = new Person();
		objectUnderTest.speichern(validPerson);
		verify(personRepositoryMock, times(1)).save(validPerson);
		assertNotNull(validPerson.getId());

	}

}
