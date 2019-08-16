package org.ahoque.library.service;

import static org.junit.Assert.assertEquals;

import org.ahoque.library.domain.Inventory;
import org.ahoque.library.domain.Title;
import org.ahoque.library.domain.TitleCopy;
import org.ahoque.library.domain.impl.DVDImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class InventoryServiceTest {

	private Inventory inventory;
	
	@Before
	public void setup() {
		inventory = new InventoryService();
	}
	
	@Test
	public void givenAnEmptyInventory_whenGetLonableItems_thenTheInventoryShouldHaveZeroItem() {
		assertEquals(0, inventory.getLoanableTitles().size());
	}
	
	@Test
	public void givenAnEmptyInventory_whenAddItem_andGetLonableItems_thenTheInventoryShouldHaveOneItem() {
		
		Title title = new TitleService("Star Wars");
		TitleCopy item = new DVDImpl("1323");
		title.addTitleCopy(item);
		inventory.addTitle(title);
		
		assertEquals(1, inventory.getLoanableTitles().size());
	}

	@Test
	public void givenTheInventoryHasTwoTitles_andOneIsOverdue_whenGetAllOverdueItems_thenTheOverdueItemShouldBeOne() {
		
		Title starWars = new TitleService("Star Wars");
		TitleCopy loanableItem = new DVDImpl("1323");
		starWars.addTitleCopy(loanableItem);
		inventory.addTitle(starWars);
		
		Title airplane = new TitleService("Airplane!");

		TitleCopy overdueItem = Mockito.mock(DVDImpl.class);
		Mockito.when(overdueItem.isOverdue()).thenReturn(true);
		
		airplane.addTitleCopy(overdueItem);
		inventory.addTitle(airplane);
		
		assertEquals(1, inventory.getOverdueTitles().size());
	}
}
