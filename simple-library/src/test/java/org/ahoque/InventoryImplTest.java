package org.ahoque;

import static org.junit.Assert.assertEquals;

import org.ahoque.Inventory;
import org.ahoque.InventoryImpl;
import org.junit.Before;
import org.junit.Test;

public class InventoryImplTest {

	private Inventory inventory;
	
	@Before
	public void setup() {
		inventory = new InventoryImpl();
	}
	
	@Test
	public void givenAnEmptyInventory_whenGetLonableItems_thenTheInventoryShouldHaveZeroItem() {
		assertEquals(0, inventory.getLoanableTitles().size());
	}
	
	@Test
	public void givenAnEmptyInventory_whenAddItem_andGetLonableItems_thenTheInventoryShouldHaveOneItem() {
		
		Title title = new TitleImpl("Star Wars", "Marvel Comics");
		TitleCopy copy = new DVDImpl("1323");
		title.add(copy);
		inventory.add(title);
		
		assertEquals(1, inventory.getLoanableTitles().size());
	}

	@Test
	public void givenTenTitles_whenTheItemsAreAdded_thenTheLoanableItemShouldBeTen() {
		
		for(int i = 0; i < 10; i++) {
			Title title = new TitleImpl("Friends part" + (i + 1), "MGM");
			TitleCopy copy = new DVDImpl("1323" + i);
			title.add(copy);
			inventory.add(title);
		}
		
		assertEquals(10, inventory.getLoanableTitles().size());
	}
}
