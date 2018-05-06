package org.ahoque.interfaces;

import java.util.List;

public interface Library {

	Member getMemberByUsername(String name);
	
	List<TitleCopy> getLoanableTitleCopiesByName(String string);
	
	void addItemToInventory(Title item);
	
	void addMember(Member member);

	List<Title> getLoanableTitles();

	Title getTitleByName(String name);

	List<TitleCopy> getOverdueItems();

	<T> List<T> getLoanableTitleCopiesByNameAndType(String name, T type);

}
