package org.ahoque.impl;

import static org.junit.Assert.*;

import org.ahoque.TitleCopy;
import org.ahoque.impl.DVDImpl;
import org.junit.Test;

public class TitleCopyImplTest {

	@Test
	public void givenTwoTitleCopies_whenTheTwoCopiesAreCompared_thenTheTwoCopiesShouldBeEqual() {
		
		TitleCopy copy1 = new DVDImpl("12345");
		TitleCopy copy2 = new DVDImpl("12345");
		
		assertTrue(copy1.equals(copy2));
	}

}
