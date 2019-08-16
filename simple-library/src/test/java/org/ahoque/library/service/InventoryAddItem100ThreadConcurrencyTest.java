package org.ahoque.library.service;

import static org.junit.Assert.*;

import org.ahoque.library.domain.Inventory;
import org.ahoque.library.domain.Title;
import org.ahoque.library.domain.TitleCopy;
import org.ahoque.library.domain.impl.DVDImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;

@RunWith(ConcurrentTestRunner.class)
public class InventoryAddItem100ThreadConcurrencyTest {

	private static final int THREAD_COUNT = 100;
	private Inventory inventory;

	@Before
	public void setup() {
		inventory = new InventoryService();
	}

	@Test
	@ThreadCount(THREAD_COUNT)
	public void givenAnEmptyInventory_whenAddItem_andGetLonableItems_thenTheInventoryShouldHaveOneItem() {
		
		Title title = new TitleService("Star Wars");
		TitleCopy item = new DVDImpl("1323");
		title.addTitleCopy(item);
		inventory.addTitle(title);
	}

	@After
	public void test() {
		assertEquals(1, inventory.getLoanableTitles().size());
	}

}
