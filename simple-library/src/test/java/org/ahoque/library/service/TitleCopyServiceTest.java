package org.ahoque.library.service;

import static org.junit.Assert.*;

import org.ahoque.library.domain.TitleCopy;
import org.ahoque.library.domain.impl.DVDImpl;
import org.junit.Test;

public class TitleCopyServiceTest {

	@Test
	public void givenTwoTitleItems_whenTheTwoItemsAreComparedUsingEquals_thenTheTwoItemsShouldNotBeEqual() {
		
		TitleCopy copy1 = new DVDImpl("12345");
		TitleCopy copy2 = new DVDImpl("678910");
		
		assertFalse(copy1.equals(copy2));
	}
	
	@Test
	public void givenTwoTitleItems_whenTheTwoItemsHashCodeAreCompared_thenTheTwoItemsHashCodeShouldNotBeEqual() {
		
		TitleCopy copy1 = new DVDImpl("12345");
		TitleCopy copy2 = new DVDImpl("678910");
		
		assertFalse(copy1.hashCode() == copy2.hashCode());
	}

}
