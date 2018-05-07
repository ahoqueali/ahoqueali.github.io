package org.ahoque.impl;

import static org.junit.Assert.*;

import org.ahoque.impl.DVDImpl;
import org.ahoque.interfaces.TitleCopy;
import org.junit.Test;

public class TitleCopyImplTest {

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
