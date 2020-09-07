package de.gothaer.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.gothaer.persistence.PersonRepository;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {
	@Mock
	private PersonRepository personRepositoryMock;
	@InjectMocks
	private PersonServiceImpl objectUnderTest;
	
	@Test
	public void speichern_parameterIsNull_throwsPersonenServiceException (){
		try {
			objectUnderTest.speichern(null);
			fail("Upps");
		} catch (PersonenServiceException e) {
			assertEquals("Parameter darf nicht null sein", e.getMessage());
		}
		
	}

}
