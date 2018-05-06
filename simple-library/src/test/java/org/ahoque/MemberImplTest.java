package org.ahoque;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MemberImplTest {

	private Member member;

	@Before
	public void setup() {
		member = new MemberImpl("ahoqueali");
	}

	@Test
	public void givenThatTheMemberHasNotBorrowedAnyItem_whenGetLoanItems_thenTheNumberOfLoanItemShouldBeZero() {
		assertEquals(member.getBorrowedItems().size(), 0);
	}
	
	@Test
	public void givenOneItem_whenTheItemIsBorrowed_thenTheNumberOfBorrowedItemsShouldBeOne() {
		
		TitleCopy item = new DVDImpl("1234");
		member.borrowItem(item);
		
		assertEquals(1, member.getBorrowedItems().size());
	}
	
	@Test
	public void givenOneItem_whenTheItemIsBorrowed_andReturned_thenTheNumberOfBorrowedItemsShouldBeZero() {
		
		TitleCopy item = new DVDImpl("1234");
		member.returnItem(item);

		assertEquals(0, member.getBorrowedItems().size());
	}

	@Test
	public void givenFourItems_whenItemsAreBorrowedAndTwoAreReturned_thenTheNumberOfBorrowedItemShouldBeTwo() {
		
		List<TitleCopy> items = new ArrayList<>();
		items.add(new DVDImpl("1234"));
		items.add(new DVDImpl("23432"));
		items.add(new DVDImpl("32432"));
		items.add(new DVDImpl("132432"));
		
		items.forEach( item -> member.borrowItem(item));
		
		member.returnItem(new DVDImpl("23432"));
		member.returnItem(new DVDImpl("132432"));

		assertEquals(2, member.getBorrowedItems().size());
		
		List<TitleCopy> expectedLoanedItems = new ArrayList<>();
		expectedLoanedItems.add(new DVDImpl("1234"));
		expectedLoanedItems.add(new DVDImpl("32432"));
		
		assertEquals(expectedLoanedItems, member.getBorrowedItems());
	}
}
