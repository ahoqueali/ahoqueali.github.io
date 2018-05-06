package org.ahoque;

import static org.junit.Assert.assertEquals;

import org.ahoque.Inventory;
import org.ahoque.InventoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
	public void givenTheInventoryHasTwoTitles_andOneIsOverdue_whenGetAllOverdueItems_thenTheOverdueItemsShouldBeOne() {
		
		Title starWars = new TitleImpl("Star Wars", "Marvel Comics");
		TitleCopy loanableItem = new DVDImpl("1323");
		starWars.add(loanableItem);
		inventory.add(starWars);
		
		Title airplane = new TitleImpl("Airplane!", "Paramount Pictures");

		TitleCopy overdueItem = Mockito.mock(DVDImpl.class);
		Mockito.when(overdueItem.isOverdue()).thenReturn(true);
		
		airplane.add(overdueItem);
		inventory.add(airplane);
		
		assertEquals(1, inventory.getOverdueTitles().size());
	}
}
