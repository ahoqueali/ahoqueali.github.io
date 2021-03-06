package org.ahoque.library.domain;

import java.util.List;

public interface Inventory {

	List<Title> getLoanableTitles();
	
	void addTitle(Title item);

	List<Title> getOverdueTitles();

	Title getTitleByName(String name);
}
