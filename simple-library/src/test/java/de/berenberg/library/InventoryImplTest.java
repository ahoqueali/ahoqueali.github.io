package de.berenberg.library;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.berenberg.library.DVDImpl;
import de.berenberg.library.Inventory;
import de.berenberg.library.InventoryImpl;
import de.berenberg.library.Title;
import de.berenberg.library.TitleCopy;
import de.berenberg.library.TitleImpl;

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
		
		Title title = new TitleImpl("Star Wars");
		TitleCopy item = new DVDImpl("1323");
		title.addTitleCopy(item);
		inventory.addTitle(title);
		
		assertEquals(1, inventory.getLoanableTitles().size());
	}

	@Test
	public void givenTheInventoryHasTwoTitles_andOneIsOverdue_whenGetAllOverdueItems_thenTheOverdueItemShouldBeOne() {
		
		Title starWars = new TitleImpl("Star Wars");
		TitleCopy loanableItem = new DVDImpl("1323");
		starWars.addTitleCopy(loanableItem);
		inventory.addTitle(starWars);
		
		Title airplane = new TitleImpl("Airplane!");

		TitleCopy overdueItem = Mockito.mock(DVDImpl.class);
		Mockito.when(overdueItem.isOverdue()).thenReturn(true);
		
		airplane.addTitleCopy(overdueItem);
		inventory.addTitle(airplane);
		
		assertEquals(1, inventory.getOverdueTitles().size());
	}
}
