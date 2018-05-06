package org.ahoque;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.ahoque.Library;
import org.ahoque.LibraryImpl;
import org.junit.Before;
import org.junit.Test;

public class LibraryImplTest {

	private static final String AHOQUEALI = "ahoqueali";
	private static final String WAR_GAMES = "WarGames";
	private static final String THE_TALE_OF_PETTER_RABBIT = "The tale of Petter Rabbit";

	private Library library;

	@Before
	public void setup() {

		library = new LibraryImpl();

		Title warGames = new TitleImpl(WAR_GAMES);
		TitleCopy warGamesCopy = new DVDImpl("D2111");
		warGames.addTitleCopy(warGamesCopy);
		library.addItemToInventory(warGames);

		Title theTaleOfPeterRabbit = new TitleImpl(THE_TALE_OF_PETTER_RABBIT);

		TitleCopy peterRabbitCopy = new BookImpl("B2234");
		theTaleOfPeterRabbit.addTitleCopy(peterRabbitCopy);

		TitleCopy peterRabbitCopy2 = new BookImpl("B2235");
		theTaleOfPeterRabbit.addTitleCopy(peterRabbitCopy2);

		library.addItemToInventory(theTaleOfPeterRabbit);

		Member member = new MemberImpl(AHOQUEALI);
		library.addMember(member);
	}

	//borrow items
	@Test
	public void givenTheTitleWarGames_whenTheMemberHoqueBorrowsTheItem_thenTheItemShouldBeLoanedToMemberHoque() {

		List<TitleCopy> copies = library.getAllLoanableTitleCopiesByName(WAR_GAMES);
		TitleCopy warGamesCopy = copies.stream().findFirst().get();

		library.getMemberByUsername(AHOQUEALI).borrowItem(warGamesCopy);
		assertTrue(library.getMemberByUsername(AHOQUEALI).getBorrowedItems().contains(warGamesCopy));
	}

	// return items
	@Test
	public void givenTheTitleWarGamesIsBorrowedByHoque_whenTheItemIsReturned_thenTheItemShouldBeRemovedFromHoquesLoanedList() {

		List<TitleCopy> copies = library.getAllLoanableTitleCopiesByName(WAR_GAMES);
		TitleCopy warGamesCopy = copies.stream().findFirst().get();
		library.getMemberByUsername(AHOQUEALI).borrowItem(warGamesCopy);

		TitleCopy borrowedCopy = library.getMemberByUsername(AHOQUEALI).getBorrowedItems().stream().findFirst().get();
		library.getMemberByUsername(AHOQUEALI).returnItem(borrowedCopy);

		assertFalse(library.getMemberByUsername(AHOQUEALI).getBorrowedItems().contains(warGamesCopy));
	}

	// borrow items for a period of one week
	@Test
	public void givenTheTitleWarGames_whenTheMemberHoqueBorrowsTheItem_thenTheItemShouldBeLoanedToMemberHoqueForSevenDays() {

		List<TitleCopy> copies = library.getAllLoanableTitleCopiesByName(WAR_GAMES);
		TitleCopy warGamesCopy = copies.stream().findFirst().get();

		library.getMemberByUsername(AHOQUEALI).borrowItem(warGamesCopy);
		TitleCopy borrowedCopy = library.getMemberByUsername(AHOQUEALI).getBorrowedItems().stream().findFirst().get();

		Loan loan = borrowedCopy.getLoan().get();

		assertEquals(7, loan.getLoanPeriod().getDays());
	}

	// determine current inventory for loanable items
	@Test
	public void givenTwoTitles_andOneIsLoaned_whenGetLoanableItems_thenTheloanbleItemsShouldBeOne() {

		List<TitleCopy> copies = library.getAllLoanableTitleCopiesByName(WAR_GAMES);
		TitleCopy warGamesCopy = copies.stream().findFirst().get();
		library.getMemberByUsername(AHOQUEALI).borrowItem(warGamesCopy);

		assertEquals(1, library.getLoanableTitles().size());
	}

	@Test
	public void givenTwoTitles_andOneTitleHasTwoCopies_andOneCopyIsLoaned_whenGetLoanableItems_thenTheloanableItemsShouldBeTwo() {

		List<TitleCopy> copies = library.getAllLoanableTitleCopiesByName(THE_TALE_OF_PETTER_RABBIT);
		TitleCopy theTaleOfPeterRabbitCopy = copies.stream().findFirst().get();
		library.getMemberByUsername(AHOQUEALI).borrowItem(theTaleOfPeterRabbitCopy);

		assertEquals(2, library.getLoanableTitles().size());
	}

	// determine all overdue items
	@Test
	public void givenOneOverdueItem_andOneNotOverdueItem_whenGetAllOverdueItems_thenTheOverdueItemShouldBeOne() {

		List<TitleCopy> copies = library.getAllLoanableTitleCopiesByName(THE_TALE_OF_PETTER_RABBIT);
		TitleCopy theTaleOfPeterRabbitCopy = copies.stream().findFirst().get();
		library.getMemberByUsername(AHOQUEALI).borrowItem(theTaleOfPeterRabbitCopy,
				new LoanImpl( 
						LocalDate.now().minusDays(8), 
						LocalDate.now().minusDays(1)));

		assertEquals(1, library.getAllOverdueItems().size());
	}


	// determine borrowed items for a user
	@Test
	public void givenMemberHoqueBorrowsTwoItems_whenGetBorrowedItemsForAMember_thenTheReturnedItemsShouldBeTheTwoBorrowedItems() {

		List<TitleCopy> expectedBorrowedItems = new ArrayList<>();

		List<TitleCopy> warGamesCopies = library.getAllLoanableTitleCopiesByName(WAR_GAMES);
		TitleCopy warGamesCopy = warGamesCopies.stream().findFirst().get();
		library.getMemberByUsername(AHOQUEALI).borrowItem(warGamesCopy);
		expectedBorrowedItems.add(warGamesCopy);

		List<TitleCopy> peterRabbitCopies = library.getAllLoanableTitleCopiesByName(THE_TALE_OF_PETTER_RABBIT);
		TitleCopy theTaleOfPeterRabbitCopy = peterRabbitCopies.stream().findFirst().get();
		library.getMemberByUsername(AHOQUEALI).borrowItem(theTaleOfPeterRabbitCopy);
		expectedBorrowedItems.add(theTaleOfPeterRabbitCopy);

		assertEquals(expectedBorrowedItems, library.getMemberByUsername(AHOQUEALI).getBorrowedItems());
	}

	// determine if a book is available to borrow  -- handle generics

	// load testing
	// tread safety testing
}
