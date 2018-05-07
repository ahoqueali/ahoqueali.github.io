package org.ahoque.concurrency;

import static org.junit.Assert.*;

import java.util.List;

import org.ahoque.impl.DVDImpl;
import org.ahoque.impl.LibraryImpl;
import org.ahoque.impl.MemberImpl;
import org.ahoque.impl.TitleImpl;
import org.ahoque.interfaces.Library;
import org.ahoque.interfaces.Title;
import org.ahoque.interfaces.TitleCopy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;

@RunWith(ConcurrentTestRunner.class)
public class LibraryMemberBorrowItemConcurrencyTest {

	private static final int THREAD_COUNT = 100;
	private Library library;
	private static final String WAR_GAMES = "WarGames";
	private static final String USERNAME = "ahoqueali";
	private TitleCopy warGamesCopy;
	private Title title;

	@Before
	public void setup() {
		library = new LibraryImpl();
		
		title = new TitleImpl(WAR_GAMES);
		warGamesCopy = new DVDImpl("1323");
		title.addTitleCopy(warGamesCopy);
		library.addItemToInventory(title);
		
		library.addMember(new MemberImpl(USERNAME));
	}

	@Test
	@ThreadCount(THREAD_COUNT)
	public void givenTheTitleWarGames_whenTheMemberHoqueBorrowsTheItem() {
		
		List<TitleCopy> items = library.getLoanableTitleCopiesByName(WAR_GAMES);
		if(items.size() > 0) {
			TitleCopy warGamesCopy = items.stream().findFirst().get();
			library.getMemberByUsername(USERNAME).borrowItem(warGamesCopy);
		}

	}

	@After
	public void thenTheItemShouldBeLoanedToMemberHoque() {
		assertTrue(library.getMemberByUsername(USERNAME).getBorrowedItems().contains(warGamesCopy));
	}

}
