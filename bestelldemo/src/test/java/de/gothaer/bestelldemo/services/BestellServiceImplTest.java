package de.gothaer.bestelldemo.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.rmi.RemoteException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.gothaer.bestelldemo.persistence.BestellRepository;
import de.gothaer.bestelldemo.persistence.model.Bestellung;

@RunWith(MockitoJUnitRunner.class)
public class BestellServiceImplTest {
	@Mock
	private BestellRepository bestellRepositoryMock;

	@Mock
	private BonitätsPrüfung bonitätsPrüfungMock;
	@InjectMocks
	private BestellServiceImpl objectUnderTest;

	private final static String VALID_MASTERCARD_NUMBER = "M0123456789";
	private final static String VALID_VISACARD_NUMBER = "V0123456789";
	private final static double VALID_AMOUNT = 100.0;
	private final static Bestellung VALID_ORDER = new Bestellung();

	@Test
	public void bestellen_WrongBestellungParameterIsNull_throwsBestellServiceException() throws Exception {
		try {
			objectUnderTest.bestellen(null, VALID_MASTERCARD_NUMBER, VALID_AMOUNT);
			fail("Uppsa");
		} catch (BestellServiceException e) {
			assertEquals("Bestellung darf nicht null sein.", e.getMessage());
		}
	}

	@Test
	public void bestellen_AmountNegative_throwsBestellServiceException() throws Exception {
		try {
			objectUnderTest.bestellen(VALID_ORDER, VALID_MASTERCARD_NUMBER, -10.0);
			fail("Uppsa");
		} catch (BestellServiceException e) {
			assertEquals("Amount darf nicht negativ sein.", e.getMessage());
		}
	}

	@Test
	public void bestellen_WrongCreditCardFormatNull_throwsBestellServiceException() throws Exception {
		try {
			objectUnderTest.bestellen(VALID_ORDER, null, VALID_AMOUNT);
			fail("Uppsa");
		} catch (BestellServiceException e) {
			assertEquals("Kreditkarte darf nicht null sein.", e.getMessage());
		}
	}

	@Test
	public void bestellen_WrongCreditCardFormattooShort_throwsBestellServiceException() throws Exception {
		try {
			objectUnderTest.bestellen(VALID_ORDER, "M012345678", VALID_AMOUNT);
			fail("Uppsa");
		} catch (BestellServiceException e) {
			assertEquals("Kreditkartennummer hat falsches Format", e.getMessage());
		}

	}

	@Test
	public void bestellen_WrongCreditCardFormattooLong_throwsBestellServiceException() throws Exception {
		try {
			objectUnderTest.bestellen(VALID_ORDER, "M01234567890", VALID_AMOUNT);
			fail("Uppsa");
		} catch (BestellServiceException e) {
			assertEquals("Kreditkartennummer hat falsches Format", e.getMessage());
		}
	}

	@Test
	public void bestellen_WrongCreditCardFormatNeitherVisaNorMaster_throwsBestellServiceException() throws Exception {
		try {
			objectUnderTest.bestellen(VALID_ORDER, "X0123456789", VALID_AMOUNT);
			fail("Uppsa");
		} catch (BestellServiceException e) {
			assertEquals("Kreditkartennummer hat falsches Format", e.getMessage());
		}
	}

	@Test
	public void bestellen_boniServiceCall_parameterAsExcepted() throws Exception {
		when(bonitätsPrüfungMock.process(anyString(), anyString(), anyDouble())).thenReturn(true);

		objectUnderTest.bestellen(VALID_ORDER, VALID_MASTERCARD_NUMBER, VALID_AMOUNT);
		verify(bonitätsPrüfungMock).process("MASTER", "0123456789", VALID_AMOUNT);
	}

	@Test(expected = KundeIstPleiteException.class)
	public void bestellen_KundeInsolvent_throwsKundeIstPleiteException() throws Exception {

		objectUnderTest.bestellen(VALID_ORDER, VALID_MASTERCARD_NUMBER, VALID_AMOUNT);
	}

	@Test
	public void bestellen_CreditcardServerDown_throwsBestellServiceException() throws Exception {
		try {
			when(bonitätsPrüfungMock.process(anyString(), anyString(), anyDouble())).thenThrow(new RemoteException());
			objectUnderTest.bestellen(VALID_ORDER, VALID_MASTERCARD_NUMBER, VALID_AMOUNT);
			fail("Uppsa");
		} catch (BestellServiceException e) {
			assertEquals("Fehler im Service", e.getMessage());
		}

	}

	@Test
	public void bestellen_RepoServerDown_throwsBestellServiceException() throws Exception {
		try {
			when(bonitätsPrüfungMock.process(anyString(), anyString(), anyDouble())).thenReturn(true);
			when(bestellRepositoryMock.saveOrUpdate((Bestellung) anyObject())).thenThrow(new RuntimeException());
			objectUnderTest.bestellen(VALID_ORDER, VALID_MASTERCARD_NUMBER, VALID_AMOUNT);
			fail("Uppsa");
		} catch (BestellServiceException e) {
			assertEquals("Fehler im Service", e.getMessage());
		}

	}

	@Test
	public void bestellen_ProperUseMaster_success() throws Exception {
		when(bonitätsPrüfungMock.process(anyString(), anyString(), anyDouble())).thenReturn(true);
		objectUnderTest.bestellen(VALID_ORDER, VALID_MASTERCARD_NUMBER, VALID_AMOUNT);
		
		InOrder inOrder = inOrder(bonitätsPrüfungMock, bestellRepositoryMock);
		inOrder.verify(bonitätsPrüfungMock).process("MASTER", "0123456789", VALID_AMOUNT);
		inOrder.verify(bestellRepositoryMock).saveOrUpdate(VALID_ORDER);
	}

	@Test
	public void bestellen_ProperUseVisa_success() throws Exception {

	}

}
