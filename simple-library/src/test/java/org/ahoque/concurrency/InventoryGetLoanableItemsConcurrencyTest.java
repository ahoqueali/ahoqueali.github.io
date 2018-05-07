package org.ahoque.concurrency;

import static org.junit.Assert.*;

import org.ahoque.impl.DVDImpl;
import org.ahoque.impl.InventoryImpl;
import org.ahoque.impl.TitleImpl;
import org.ahoque.interfaces.Inventory;
import org.ahoque.interfaces.Title;
import org.ahoque.interfaces.TitleCopy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;

@RunWith(ConcurrentTestRunner.class)
public class InventoryGetLoanableItemsConcurrencyTest {

	private static final int THREAD_COUNT = 5;
	private Inventory inventory;

	@Before
	public void setup() {
		inventory = new InventoryImpl();
	}

	@Test
	@ThreadCount(THREAD_COUNT)
	public void givenAnEmptyInventory_whenAddItem_andGetLonableItems() {
		
		Title title = new TitleImpl("Star Wars");
		TitleCopy item = new DVDImpl("1323");
		title.addTitleCopy(item);
		inventory.addTitle(title);
	}

	@After
	public void thenTheInventoryShouldHaveOneItem() {
		assertEquals(1, inventory.getLoanableTitles().size());
	}

}
