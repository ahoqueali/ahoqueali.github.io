package org.ahoque.library.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.ahoque.library.domain.Member;
import org.ahoque.library.domain.TitleCopy;
import org.ahoque.library.domain.impl.DVDImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;

@RunWith(ConcurrentTestRunner.class)
public class MemberBorrowItem100ThreadConcurrencyTest {

	private static final int THREAD_COUNT = 100;
	private Member member;
	
	@Before
	public void init() {
		member = new MemberService("ahoquealli");
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
