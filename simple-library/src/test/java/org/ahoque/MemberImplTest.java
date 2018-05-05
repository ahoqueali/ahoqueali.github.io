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
		assertEquals(member.getLoanItems().size(), 0);
	}
	
	@Test
	public void givenFourItems_whenTheFourItemsAreBorrowed_thenTheNumberOfBorrowedItemsShouldBeFour() {
		
		List<TitleCopy> items = new ArrayList<>();
		items.add(new TitleCopyImpl("1234"));
		items.add(new TitleCopyImpl("23432"));
		items.add(new TitleCopyImpl("32432"));
		items.add(new TitleCopyImpl("132432"));
		
		items.forEach( item -> member.borrowItem(item));
		
		assertEquals(4, member.getLoanItems().size());
		assertEquals(member.getLoanItems(), items);
	}

	@Test
	public void givenFourItems_whenItemsAreBorrowedAndTwoAreReturned_thenTheNumberOfBorrowedItemShouldBeTwo() {
		
		List<TitleCopy> items = new ArrayList<>();
		items.add(new TitleCopyImpl("1234"));
		items.add(new TitleCopyImpl("23432"));
		items.add(new TitleCopyImpl("32432"));
		items.add(new TitleCopyImpl("132432"));
		
		items.forEach( item -> member.borrowItem(item));
		
		
		member.returnItem(new TitleCopyImpl("23432"));
		member.returnItem(new TitleCopyImpl("132432"));

		assertEquals(2, member.getLoanItems().size());
		
		List<TitleCopy> expectedLoanedItems = new ArrayList<>();
		expectedLoanedItems.add(new TitleCopyImpl("1234"));
		expectedLoanedItems.add(new TitleCopyImpl("32432"));
		
		assertEquals(expectedLoanedItems, member.getLoanItems());
		
		
	}
}
