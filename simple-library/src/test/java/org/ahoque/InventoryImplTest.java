package org.ahoque;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
	public void givenTheTitleStarWars_whenAddTitle_thenTheLoanedItemShouldBeStarWars_andTheInventoryShouldHaveOneItem() {
		
		Title title = new TitleImpl("Star Wars", "Marvel Comics");
		
		TitleCopy copy = new TitleCopyImpl("1323");
		title.add(copy);
		
		inventory.add(title);
		assertEquals(1, inventory.getLoanableTitles().size());
		
		List<Title> expectedTitle = new ArrayList<>();
		expectedTitle.add(title);
		assertEquals(expectedTitle, inventory.getLoanableTitles());
	}
	
	

}
