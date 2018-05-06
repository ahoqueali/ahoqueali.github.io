package org.ahoque;

import java.util.List;

public interface Inventory {

	List<Title> getLoanableTitles();
	
	void addTitle(Title item);

	List<Title> getOverdueTitles();

	Title getTitleByName(String name);
}
