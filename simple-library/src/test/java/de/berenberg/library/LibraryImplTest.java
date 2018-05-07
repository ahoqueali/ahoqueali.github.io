package de.berenberg.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.berenberg.library.Book;
import de.berenberg.library.BookImpl;
import de.berenberg.library.DVDImpl;
import de.berenberg.library.Library;
import de.berenberg.library.LibraryImpl;
import de.berenberg.library.Loan;
import de.berenberg.library.LoanImpl;
import de.berenberg.library.Member;
import de.berenberg.library.MemberImpl;
import de.berenberg.library.Title;
import de.berenberg.library.TitleCopy;
import de.berenberg.library.TitleImpl;

public class LibraryImplTest {

	private static final String USERNAME = "ahoqueali";
	private static final String WAR_GAMES = "WarGames";
	private static final String THE_TALE_OF_PETTER_RABBIT = "The tale of Petter Rabbit";

	private Library library;
	
	private TitleCopy peterRabbitBookCopy;

	@Before
	public void setup() {

		library = new LibraryImpl();

		Title warGames = new TitleImpl(WAR_GAMES);
		TitleCopy warGamesCopy = new DVDImpl("D2111");
		warGames.addTitleCopy(warGamesCopy);
		library.addItemToInventory(warGames);

		Title theTaleOfPeterRabbit = new TitleImpl(THE_TALE_OF_PETTER_RABBIT);

		peterRabbitBookCopy = new BookImpl("B2234");
		theTaleOfPeterRabbit.addTitleCopy(peterRabbitBookCopy);

		TitleCopy peterRabbitDvdCopy = new DVDImpl("B2235");
		theTaleOfPeterRabbit.addTitleCopy(peterRabbitDvdCopy);

		library.addItemToInventory(theTaleOfPeterRabbit);

		Member member = new MemberImpl(USERNAME);
		library.addMember(member);
	}

	// borrow items
	@Test
	public void givenTheTitleWarGames_whenTheMemberHoqueBorrowsTheItem_thenTheItemShouldBeLoanedToMemberHoque() {

		List<TitleCopy> items = library.getLoanableTitleCopiesByName(WAR_GAMES);
		TitleCopy warGamesCopy = items.stream().findFirst().get();

		library.getMemberByUsername(USERNAME).borrowItem(warGamesCopy);
		assertTrue(library.getMemberByUsername(USERNAME).getBorrowedItems().contains(warGamesCopy));
	}

	// return items
	@Test
	public void givenTheTitleWarGamesIsBorrowedByHoque_whenTheItemIsReturned_thenTheItemShouldBeRemovedFromHoquesLoanedList() {

		List<TitleCopy> items = library.getLoanableTitleCopiesByName(WAR_GAMES);
		TitleCopy warGamesCopy = items.stream().findFirst().get();
		library.getMemberByUsername(USERNAME).borrowItem(warGamesCopy);

		TitleCopy borrowedCopy = library.getMemberByUsername(USERNAME).getBorrowedItems().stream().findFirst().get();
		library.getMemberByUsername(USERNAME).returnItem(borrowedCopy);

		assertFalse(library.getMemberByUsername(USERNAME).getBorrowedItems().contains(warGamesCopy));
	}

	// borrow items for a period of one week
	@Test
	public void givenTheTitleWarGames_whenTheMemberHoqueBorrowsTheItem_thenTheItemShouldBeLoanedToMemberHoqueForSevenDays() {

		List<TitleCopy> items = library.getLoanableTitleCopiesByName(WAR_GAMES);
		TitleCopy warGamesCopy = items.stream().findFirst().get();
		library.getMemberByUsername(USERNAME).borrowItem(warGamesCopy);
		
		TitleCopy borrowedCopy = library.getMemberByUsername(USERNAME).getBorrowedItems().stream().findFirst().get();
		Loan loan = borrowedCopy.getLoan().get();

		assertEquals(7, loan.getLoanPeriod().getDays());
	}

	// determine current inventory for loanable items
	@Test
	public void givenTwoTitles_andOneIsLoaned_whenGetLoanableItems_thenTheloanbleItemsShouldBeOne() {

		List<TitleCopy> items = library.getLoanableTitleCopiesByName(WAR_GAMES);
		TitleCopy warGamesCopy = items.stream().findFirst().get();
		library.getMemberByUsername(USERNAME).borrowItem(warGamesCopy);

		assertEquals(1, library.getLoanableTitles().size());
	}

	@Test
	public void givenTwoTitles_andOneTitleHasTwoCopies_andOneCopyIsLoaned_whenGetLoanableItems_thenTheloanableItemsShouldBeTwo() {

		List<TitleCopy> items = library.getLoanableTitleCopiesByName(THE_TALE_OF_PETTER_RABBIT);
		TitleCopy theTaleOfPeterRabbitCopy = items.stream().findFirst().get();
		library.getMemberByUsername(USERNAME).borrowItem(theTaleOfPeterRabbitCopy);

		assertEquals(2, library.getLoanableTitles().size());
	}

	// determine all overdue items
	@Test
	public void givenOneOverdueItem_andOneNotOverdueItem_whenGetAllOverdueItems_thenTheOverdueItemShouldBeOne() {

		List<TitleCopy> items = library.getLoanableTitleCopiesByName(THE_TALE_OF_PETTER_RABBIT);
		TitleCopy theTaleOfPeterRabbitCopy = items.stream().findFirst().get();
		library.getMemberByUsername(USERNAME).borrowItem(theTaleOfPeterRabbitCopy,
				new LoanImpl( 
						LocalDate.now().minusDays(8), 
						LocalDate.now().minusDays(1)));

		assertEquals(1, library.getOverdueItems().size());
	}


	// determine borrowed items for a user
	@Test
	public void givenMemberHoqueBorrowsTwoItems_whenGetBorrowedItemsForAMember_thenTheReturnedItemsShouldBeTheTwoBorrowedItems() {

		List<TitleCopy> expectedBorrowedItems = new ArrayList<>();

		List<TitleCopy> warGamesCopies = library.getLoanableTitleCopiesByName(WAR_GAMES);
		TitleCopy warGamesCopy = warGamesCopies.stream().findFirst().get();
		library.getMemberByUsername(USERNAME).borrowItem(warGamesCopy);
		expectedBorrowedItems.add(warGamesCopy);

		List<TitleCopy> peterRabbitCopies = library.getLoanableTitleCopiesByName(THE_TALE_OF_PETTER_RABBIT);
		TitleCopy theTaleOfPeterRabbitCopy = peterRabbitCopies.stream().findFirst().get();
		library.getMemberByUsername(USERNAME).borrowItem(theTaleOfPeterRabbitCopy);
		expectedBorrowedItems.add(theTaleOfPeterRabbitCopy);

		assertEquals(expectedBorrowedItems, library.getMemberByUsername(USERNAME).getBorrowedItems());
	}

	// determine if a book is available to borrow
	@Test
	public void givenTheLoanableBookTaleOfPeterRabbit_whenGetLonableTitleItemByTypeBook_thenTheBookTheTaleOfPeterRabbitShouldBeReturned() {
		
		List<Book> peterRabbitCopies = library.getLoanableTitleCopiesByNameAndType(THE_TALE_OF_PETTER_RABBIT, Book.class);
		assertEquals(peterRabbitCopies.get(0), peterRabbitBookCopy);
	}

}
