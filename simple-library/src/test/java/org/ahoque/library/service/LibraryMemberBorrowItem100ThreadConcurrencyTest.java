package org.ahoque.library.service;

import static org.junit.Assert.*;

import java.util.List;

import org.ahoque.library.domain.impl.DVDImpl;
import org.ahoque.library.domain.Library;
import org.ahoque.library.domain.Title;
import org.ahoque.library.domain.TitleCopy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;

@RunWith(ConcurrentTestRunner.class)
public class LibraryMemberBorrowItem100ThreadConcurrencyTest {

	private static final int THREAD_COUNT = 100;
	private Library library;
	private static final String WAR_GAMES = "WarGames";
	private static final String USERNAME = "ahoqueali";
	private TitleCopy warGamesCopy;
	private Title title;

	@Before
	public void setup() {
		library = new LibraryService();
		
		title = new TitleService(WAR_GAMES);
		warGamesCopy = new DVDImpl("1323");
		title.addTitleCopy(warGamesCopy);
		library.addItemToInventory(title);
		
		library.addMember(new MemberService(USERNAME));
	}

	@Test
	@ThreadCount(THREAD_COUNT)
	public void givenTheTitleWarGames_whenTheMemberHoqueBorrowsTheItem_thenTheItemShouldBeLoanedToMemberHoque() throws NoSuchMemberException {
		
		List<TitleCopy> items = library.getLoanableTitleCopiesByName(WAR_GAMES);
		if(items.size() > 0) {
			TitleCopy warGamesCopy = items.stream().findFirst().get();
			library.getMemberByUsername(USERNAME).borrowItem(warGamesCopy);
		}

	}

	@After
	public void test() throws NoSuchMemberException {
		assertTrue(library.getMemberByUsername(USERNAME).getBorrowedItems().contains(warGamesCopy));
	}

}
