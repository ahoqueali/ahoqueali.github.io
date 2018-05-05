package org.ahoque;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.ahoque.Library;
import org.ahoque.LibraryImpl;
import org.junit.Before;
import org.junit.Test;

public class LibraryImplTest {

	private static final int LOAN_PERIOD_IN_DAYS = 7;
	private static final String UNIQUE_ID = "2221";
	private Library library;
	
	@Before
	public void setup() {

		library = new LibraryImpl();
		
		Title title = new TitleImpl("WarGames", "Metro-Goldwyn-Mayer Studios Inc");
		TitleCopy warGamesCopy = new DVDImpl(UNIQUE_ID);
		title.add(warGamesCopy);
		library.addItemToInventory(title);
				
		Member member = new MemberImpl("ahoqueali");
		library.addMember(member);
	}
	
	@Test
	public void givenTheTitleWarGames_whenTheMemberHoqueBorrowsTheItem_thenTheItemShouldBeLoanedToMemberHoque() {
	
		List<TitleCopy> copies = library.findTileCopiesByName("WarGames");
		TitleCopy warGamesCopy = copies.stream().findFirst().get();
		
		library.getMemberByUsername("ahoqueali").borrowItem(warGamesCopy);
		assertTrue(library.getMemberByUsername("ahoqueali").getLoanItems().contains(warGamesCopy));
	}

	@Test
	public void givenTheTitleWarGamesIsBorrowedByHoque_whenTheItemIsReturned_thenTheItemShouldBeRemovedFromHoquesLoanedList() {
	
		List<TitleCopy> copies = library.findTileCopiesByName("WarGames");
		TitleCopy warGamesCopy = copies.stream().findFirst().get();
		library.getMemberByUsername("ahoqueali").borrowItem(warGamesCopy);
		
		TitleCopy borrowedCopy = library.getMemberByUsername("ahoqueali").getLoanItems().stream().findFirst().get();
		library.getMemberByUsername("ahoqueali").returnItem(borrowedCopy);
		
		assertFalse(library.getMemberByUsername("ahoqueali").getLoanItems().contains(warGamesCopy));
	}
	
	@Test
	public void givenTheTitleWarGames_whenTheMemberHoqueBorrowsTheItem_thenTheItemShouldBeLoanedToMemberHoqueForOneWeek() {
	
		List<TitleCopy> copies = library.findTileCopiesByName("WarGames");
		TitleCopy warGamesCopy = copies.stream().findFirst().get();
		
		library.getMemberByUsername("ahoqueali").borrowItem(warGamesCopy);
		TitleCopy borrowedCopy = library.getMemberByUsername("ahoqueali").getLoanItems().stream().findFirst().get();
		
		Loan loan = borrowedCopy.getLoan().get();
		assertEquals(LOAN_PERIOD_IN_DAYS, loan.getPeriod().getDays());
	}
}
