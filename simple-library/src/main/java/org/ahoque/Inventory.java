package org.ahoque;

import java.util.Collection;

public interface Inventory {

	Collection<Title> getLoanableTitles();
	
	void add(Title item);
}
