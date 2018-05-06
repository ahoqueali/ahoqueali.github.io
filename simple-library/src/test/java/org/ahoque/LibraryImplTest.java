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
	private Library library;
	
	@Before
	public void setup() {

		library = new LibraryImpl();
		
		Title warGames = new TitleImpl("WarGames", "Metro-Goldwyn-Mayer Studios Inc");
		TitleCopy warGamesCopy = new DVDImpl("D2111");
		warGames.add(warGamesCopy);
		library.addItemToInventory(warGames);
		
		Title theTaleOfPeterRabbit = new TitleImpl("The tale of Petter Rabbit", "Frederick Warne & co");
		
		TitleCopy peterRabbitCopy = new BookImpl("B2234");
		theTaleOfPeterRabbit.add(peterRabbitCopy);
		
		TitleCopy peterRabbitCopy2 = new BookImpl("B2235");
		theTaleOfPeterRabbit.add(peterRabbitCopy2);
		
		library.addItemToInventory(theTaleOfPeterRabbit);
		
		Member member = new MemberImpl("ahoqueali");
		library.addMember(member);
	}
	
	//borrow items
	@Test
	public void givenTheTitleWarGames_whenTheMemberHoqueBorrowsTheItem_thenTheItemShouldBeLoanedToMemberHoque() {
	
		List<TitleCopy> copies = library.findLoanableTileCopiesByName("WarGames");
		TitleCopy warGamesCopy = copies.stream().findFirst().get();
		
		library.getMemberByUsername("ahoqueali").borrowItem(warGamesCopy);
		assertTrue(library.getMemberByUsername("ahoqueali").getLoanItems().contains(warGamesCopy));
	}

	// return items
	@Test
	public void givenTheTitleWarGamesIsBorrowedByHoque_whenTheItemIsReturned_thenTheItemShouldBeRemovedFromHoquesLoanedList() {
	
		List<TitleCopy> copies = library.findLoanableTileCopiesByName("WarGames");
		TitleCopy warGamesCopy = copies.stream().findFirst().get();
		library.getMemberByUsername("ahoqueali").borrowItem(warGamesCopy);
		
		TitleCopy borrowedCopy = library.getMemberByUsername("ahoqueali").getLoanItems().stream().findFirst().get();
		library.getMemberByUsername("ahoqueali").returnItem(borrowedCopy);
		
		assertFalse(library.getMemberByUsername("ahoqueali").getLoanItems().contains(warGamesCopy));
	}
	
	// borrow items for a period of one week
	@Test
	public void givenTheTitleWarGames_whenTheMemberHoqueBorrowsTheItem_thenTheItemShouldBeLoanedToMemberHoqueForSevenDays() {
	
		List<TitleCopy> copies = library.findLoanableTileCopiesByName("WarGames");
		TitleCopy warGamesCopy = copies.stream().findFirst().get();
		
		library.getMemberByUsername("ahoqueali").borrowItem(warGamesCopy);
		TitleCopy borrowedCopy = library.getMemberByUsername("ahoqueali").getLoanItems().stream().findFirst().get();
		
		Loan loan = borrowedCopy.getLoan().get();
		assertEquals(LOAN_PERIOD_IN_DAYS, loan.getPeriod().getDays());
	}
	
	
	// determine current inventory for loanable items
	@Test
	public void givenTwoTitles_andOneIsLoaned_whenGetLoanableItems_thenTheloableItemsShouldBeOne() {
		
		List<TitleCopy> copies = library.findLoanableTileCopiesByName("WarGames");
		TitleCopy warGamesCopy = copies.stream().findFirst().get();
		library.getMemberByUsername("ahoqueali").borrowItem(warGamesCopy);
		
		assertEquals(1, library.getLoanableTitles().size());
	}
	
	@Test
	public void givenTwoTitles_andOneTitleHasTwoCopies_andOneCopyIsLoaned_whenGetLoanableItems_thenTheloanableItemsShouldBeTwo() {
		
		List<TitleCopy> copies = library.findLoanableTileCopiesByName("The tale of Petter Rabbit");
		TitleCopy theTableOfPeterRabbitCopy = copies.stream().findFirst().get();
		library.getMemberByUsername("ahoqueali").borrowItem(theTableOfPeterRabbitCopy);
		
		assertEquals(2, library.getLoanableTitles().size());
	}
	
	// determine all overdue items
	
	// determine borrowed items for a user
	
	// determine if a book is available to borrow
	
	
}
