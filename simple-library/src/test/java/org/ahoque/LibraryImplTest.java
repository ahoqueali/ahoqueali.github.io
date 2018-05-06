package org.ahoque;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
		assertTrue(library.getMemberByUsername("ahoqueali").getBorrowedItems().contains(warGamesCopy));
	}

	// return items
	@Test
	public void givenTheTitleWarGamesIsBorrowedByHoque_whenTheItemIsReturned_thenTheItemShouldBeRemovedFromHoquesLoanedList() {

		List<TitleCopy> copies = library.findLoanableTileCopiesByName("WarGames");
		TitleCopy warGamesCopy = copies.stream().findFirst().get();
		library.getMemberByUsername("ahoqueali").borrowItem(warGamesCopy);

		TitleCopy borrowedCopy = library.getMemberByUsername("ahoqueali").getBorrowedItems().stream().findFirst().get();
		library.getMemberByUsername("ahoqueali").returnItem(borrowedCopy);

		assertFalse(library.getMemberByUsername("ahoqueali").getBorrowedItems().contains(warGamesCopy));
	}

	// borrow items for a period of one week
	@Test
	public void givenTheTitleWarGames_whenTheMemberHoqueBorrowsTheItem_thenTheItemShouldBeLoanedToMemberHoqueForSevenDays() {

		List<TitleCopy> copies = library.findLoanableTileCopiesByName("WarGames");
		TitleCopy warGamesCopy = copies.stream().findFirst().get();

		library.getMemberByUsername("ahoqueali").borrowItem(warGamesCopy);
		TitleCopy borrowedCopy = library.getMemberByUsername("ahoqueali").getBorrowedItems().stream().findFirst().get();

		Loan loan = borrowedCopy.getLoan().get();
		assertEquals(LOAN_PERIOD_IN_DAYS, loan.getPeriod().getDays());
	}

	// determine current inventory for loanable items
	@Test
	public void givenTwoTitles_andOneIsLoaned_whenGetLoanableItems_thenTheloanbleItemsShouldBeOne() {

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
	@Test
	public void givenTwoOverdueItems_andFiveNotOverDueItems_whenGetAllOverDueItems_thenTheOverdueItemsShouldBeTwo() {

//		for(int i = 0; i < 5; i++) {
//
//			List<TitleCopy> copies = library.findLoanableTileCopiesByName("not over due item" + i);
//			TitleCopy theTableOfPeterRabbitCopy = copies.stream().findFirst().get();
//			library.getMemberByUsername("ahoqueali").borrowItem(theTableOfPeterRabbitCopy);
//		}

	}


	// determine borrowed items for a user
	@Test
	public void givenMemberHoqueBorrowsTwoItems_whenGetBorrowedItemsForAMember_thenTheReturnedItemsShouldBeTheTwoBorrowedItems() {

		List<TitleCopy> expectedBorrowedItems = new ArrayList<>();

		List<TitleCopy> warGamesCopies = library.findLoanableTileCopiesByName("WarGames");
		TitleCopy warGamesCopy = warGamesCopies.stream().findFirst().get();
		library.getMemberByUsername("ahoqueali").borrowItem(warGamesCopy);
		expectedBorrowedItems.add(warGamesCopy);

		List<TitleCopy> peterRabbitCopies = library.findLoanableTileCopiesByName("The tale of Petter Rabbit");
		TitleCopy theTableOfPeterRabbitCopy = peterRabbitCopies.stream().findFirst().get();
		library.getMemberByUsername("ahoqueali").borrowItem(theTableOfPeterRabbitCopy);
		expectedBorrowedItems.add(theTableOfPeterRabbitCopy);

		assertEquals(expectedBorrowedItems, library.getMemberByUsername("ahoqueali").getBorrowedItems());
	}

	// determine if a book is available to borrow  -- handle generics

	// load testing
	// tread safety testing
}
