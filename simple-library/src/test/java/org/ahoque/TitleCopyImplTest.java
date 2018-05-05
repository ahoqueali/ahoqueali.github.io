package org.ahoque;

import static org.junit.Assert.*;

import org.junit.Test;

public class TitleCopyImplTest {

	@Test
	public void givenTwoTitleCopies_whenEqualTest_thenTheTwoCopiesShouldBeEqual() {
		
		TitleCopy copy1 = new TitleCopyImpl("12345");
		TitleCopy copy2 = new TitleCopyImpl("12345");
		
		assertTrue(copy1.equals(copy2));
	}

}
