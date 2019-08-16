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
public class MemberReturnItem100ThreadConcurrencyTest {

	private static final int THREAD_COUNT = 5;
	private Member member;
	
	private TitleCopy item;
	
	@Before
	public void init() {
		member = new MemberService("ahoquealli");
		item = new DVDImpl("234");
		member.borrowItem(item);
	}
	
	@Test
	@ThreadCount(THREAD_COUNT)
	public void givenAnItemWithId234_whenReturnItem_thenTheBorrowedItemShouldBeItemWithId234() {
		member.returnItem(item);
	}
	
	@After
	public void test() {
		List<TitleCopy> expectedLoanedItems = new ArrayList<>();
		assertEquals(expectedLoanedItems, member.getBorrowedItems());
	}

}
