package de.collections;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StapelTest {
	
	private Stapel objectUnderTest;
	
	@Before
	public void setUp() {
		objectUnderTest = new Stapel();
	}

	@Test
	// GivenEmptyStackWhenIsEmptyCalledThenReturnsTrue
	public void isEmpty_leererStapel_returnsTrue () {
		// Arrange
		
		// Action
		boolean ergebnis = objectUnderTest.isEmpty();
		
		// Assertion
		assertTrue(ergebnis);
	}

	@Test
	public void isEmpty_nichtLeererStapel_returnsFalse () throws Exception{
		
		// Arrange
		objectUnderTest.push(new Object());
		// Action
		boolean ergebnis = objectUnderTest.isEmpty();
		
		// Assertion
		assertFalse(ergebnis);
	}

	@Test
	public void isEmpty_WiederLeererStapel_returnsFalse () throws Exception{
		
		// Arrange
		objectUnderTest.push(new Object());
		objectUnderTest.pop();
		// Action
		boolean ergebnis = objectUnderTest.isEmpty();
		
		// Assertion
		assertTrue(ergebnis);
		
	}
	
	@Test
	public void push_leererStapel_valueIsStored() throws Exception {
		// Arrange
		final Object object = new Object();
		
		// Action
		objectUnderTest.push(object);
		
		
		// Assertion
		assertEquals(object, objectUnderTest.pop());
	}

	@Test
	public void push_fillToUpperLimit_noExceptionIsThrown() throws Exception {
		// Arrange
		final Object object = new Object();
		
		fillUpToLimit(object);
		
		// Assertion
		// Ok
	}

	@Test(expected = StapelException.class)
	public void push_Overflow_throwsStapelException() throws Exception {
		// Arrange
		final Object object = new Object();
		
		fillUpToLimit(object);
		objectUnderTest.push(object);
		// Assertion
		// Ok
	}
	@Test
	public void push_Overflow_throwsStapelException_Variante2() throws Exception {
		try {
			// Arrange
			final Object object = new Object();
			
			// Action
			fillUpToLimit(object);
			objectUnderTest.push(object);
			
			fail("Upps");
			
		} catch (StapelException e) {
			assertEquals("Overflow", e.getMessage());
		}
	}

	private void fillUpToLimit(final Object object) throws StapelException {
		for (int i = 0; i < 10; i++) {
			objectUnderTest.push(object);
		}
	}


}
