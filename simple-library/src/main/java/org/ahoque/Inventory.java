package org.ahoque;

import java.util.List;

public interface Inventory {

	List<Title> getLoanableTitles();
	
	void add(Title item);
}
