package de.gothaer.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.gothaer.math.Calculator;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalcClientTest {
	@Mock
	private Calculator calculatorMock;
	@InjectMocks
	private CalcClient objectUnderTest;
	
	@Test
	public void go____() {
		when(calculatorMock.add(3.0, 4.0)).thenReturn(42.0);
		when(calculatorMock.add(3.0,5.0)).thenReturn(67.0);
		
		// Action
		objectUnderTest.go();
		
		
		//Assertion
		verify(calculatorMock, times(1)).add(3.0, 4.0);
		verify(calculatorMock, times(1)).add(3.0, 5.0);

	}

}
