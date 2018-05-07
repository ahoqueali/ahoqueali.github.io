package de.berenberg.library;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;

import de.berenberg.library.DVDImpl;
import de.berenberg.library.Inventory;
import de.berenberg.library.InventoryImpl;
import de.berenberg.library.Title;
import de.berenberg.library.TitleCopy;
import de.berenberg.library.TitleImpl;

@RunWith(ConcurrentTestRunner.class)
public class InventoryAddItem100ThreadConcurrencyTest {

	private static final int THREAD_COUNT = 100;
	private Inventory inventory;

	@Before
	public void setup() {
		inventory = new InventoryImpl();
	}

	@Test
	@ThreadCount(THREAD_COUNT)
	public void givenAnEmptyInventory_whenAddItem_andGetLonableItems_thenTheInventoryShouldHaveOneItem() {
		
		Title title = new TitleImpl("Star Wars");
		TitleCopy item = new DVDImpl("1323");
		title.addTitleCopy(item);
		inventory.addTitle(title);
	}

	@After
	public void test() {
		assertEquals(1, inventory.getLoanableTitles().size());
	}

}
