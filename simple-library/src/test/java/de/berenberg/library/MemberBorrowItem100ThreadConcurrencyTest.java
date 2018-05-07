package de.berenberg.library;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;

import de.berenberg.library.DVDImpl;
import de.berenberg.library.Member;
import de.berenberg.library.MemberImpl;
import de.berenberg.library.TitleCopy;

@RunWith(ConcurrentTestRunner.class)
public class MemberBorrowItem100ThreadConcurrencyTest {

	private static final int THREAD_COUNT = 100;
	private Member member;
	
	@Before
	public void init() {
		member = new MemberImpl("ahoquealli");
	}
	
	@Test
	@ThreadCount(THREAD_COUNT)
	public void givenAnItemWithId234_whenBorrowItem_thenTheBorrowedItemShouldBeItemWithId234() {

		TitleCopy item = new DVDImpl("234");
		member.borrowItem(item);
	}
	
	@After
	public void test() {
		List<TitleCopy> expectedLoanedItems = new ArrayList<>();
		expectedLoanedItems.add(new DVDImpl("234"));
		assertEquals(expectedLoanedItems, member.getBorrowedItems());
	}

}
