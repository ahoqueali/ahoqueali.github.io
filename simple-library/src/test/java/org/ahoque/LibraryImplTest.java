package org.ahoque;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.ahoque.Library;
import org.ahoque.LibraryImpl;
import org.junit.Before;
import org.junit.Test;

public class LibraryImplTest {

	private Library library;
	
	@Before
	public void setup() {

		library = new LibraryImpl();
		
		Title title = new TitleImpl("WarGames", "Metro-Goldwyn-Mayer Studios Inc");
		TitleCopy warGamesCopy = new TitleCopyImpl("2221");
		title.add(warGamesCopy);
		library.addItemToInventory(title);
				
		Member member = new MemberImpl("ahoqueali");
		library.addMember(member);
	}
	
	@Test
	public void givenTheTitleWarGames_whenTheMemberHoqueBorrowsTheItem_ThenTheItemShouldBeLoanedToMemberHoque() {
	
		List<TitleCopy> copies = library.findTileCopiesByName("WarGames");
		TitleCopy warGamesCopy = copies.stream().findFirst().get();
		assertTrue(warGamesCopy.isLoanable());
		
		library.getMemberByUsername("ahoqueali").borrowItem(warGamesCopy);
		assertTrue(library.getMemberByUsername("ahoqueali").getLoanItems().contains(warGamesCopy));
	}
	
	@Test
	public void givenTheTitleWarGames_whenTheMemberHoqueBorrowsTheItem_ThenTheItemShouldBeLoanedToMemberHoqueAndForOneWeek() {
	
		List<TitleCopy> copies = library.findTileCopiesByName("WarGames");
		TitleCopy warGamesCopy = copies.stream().findFirst().get();
		assertTrue(warGamesCopy.isLoanable());
		
		library.getMemberByUsername("ahoqueali").borrowItem(warGamesCopy);
		
		TitleCopy borrowedCopy = library.getMemberByUsername("ahoqueali").getLoanItems().stream().findFirst().get();
		assertEquals(warGamesCopy, borrowedCopy);
		
		Loan loan = borrowedCopy.getLoan().get();
		assertEquals(7, loan.getPeriod().getDays());
	}
	
//	@Test
//	public void givenMemberHoqueHasSevenOverdueItems_whenMemberHoqueChecksForOverDueItems_ThenTheOverdueItemsShouldBeSeven() {
//	
//		List<TitleCopy> copies = library.findTileCopiesByName("WarGames");
//		TitleCopy warGamesCopy = copies.stream().findFirst().get();
//		assertTrue(warGamesCopy.isLoanable());
//		
//		library.getMemberByUsername("ahoqueali").borrowItem(warGamesCopy);
//		
//		TitleCopy borrowedCopy = library.getMemberByUsername("ahoqueali").getLoanItems().stream().findFirst().get();
//		assertEquals(warGamesCopy, borrowedCopy);
//		
//		Loan loan = borrowedCopy.getLoan().get();
//		assertEquals(7, loan.getPeriod().getDays());
//	}
}
