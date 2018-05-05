package org.ahoque;

import java.util.List;

public interface Library {

	Member getMemberByUsername(String name);
	
	List<TitleCopy> findTileCopiesByName(String string);
	
	void addItemToInventory(Title item);
	
	void addMember(Member member);
}
