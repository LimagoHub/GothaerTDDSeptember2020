package de.gothaer.multi.mockdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MyServiceImplTest {
	@Mock
	private MyDependency myDependencyMock;
	@InjectMocks
	private MyServiceImpl objectUnderTest;
	
	@Test(expected = MyException.class)
	public void footest1() {
		
		// Das gilt nur für Void Methoden
		
		//doNothing().when(myDependencyMock).foo(anyString());
		doThrow(new ArrayIndexOutOfBoundsException("Upps")).when(myDependencyMock).foo(anyString());
		objectUnderTest.tueWas();
		//verify(myDependencyMock).foo("Hallo");
	}

	@Test(expected = MyException.class)
	public void bartest1() {
		
		// Gilt für nicht void methoden
		
		when(myDependencyMock.bar()).thenThrow(new ArrayIndexOutOfBoundsException());
		assertEquals(20, objectUnderTest.tueWasAnderes());
		//verify(myDependencyMock, times(1)).bar();
	}

}
