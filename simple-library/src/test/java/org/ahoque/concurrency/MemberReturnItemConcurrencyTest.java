package org.ahoque.concurrency;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.ahoque.impl.DVDImpl;
import org.ahoque.impl.MemberImpl;
import org.ahoque.interfaces.Member;
import org.ahoque.interfaces.TitleCopy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;

@RunWith(ConcurrentTestRunner.class)
public class MemberReturnItemConcurrencyTest {

	private static final int THREAD_COUNT = 5;
	private Member member;
	
	private TitleCopy item;
	
	@Before
	public void init() {
		member = new MemberImpl("ahoquealli");
		item = new DVDImpl("234");
		member.borrowItem(item);
	}
	
	@Test
	@ThreadCount(THREAD_COUNT)
	public void givenAnItemWithId234_whenReturnItem() {
		member.returnItem(item);
	}
	
	@After
	public void thenTheBorrowedItemShouldBeItemWithId234() {
		List<TitleCopy> expectedLoanedItems = new ArrayList<>();
		assertEquals(expectedLoanedItems, member.getBorrowedItems());
	}

}
