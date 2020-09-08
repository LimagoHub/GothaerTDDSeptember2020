package de.gothaer.bestelldemo.services;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.gothaer.bestelldemo.persistence.BestellRepository;

@RunWith(MockitoJUnitRunner.class)
public class BestellServiceImplTest {
	@Mock
	private BestellRepository bestellRepositoryMock;

	@Mock
	private BonitätsPrüfung bonitätsPrüfungMock;
	@InjectMocks
	private BestellServiceImpl objectUnderTest;

}
