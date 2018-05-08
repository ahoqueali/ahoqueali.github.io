package de.berenberg.library;

import java.util.List;

public interface Library {

	Member getMemberByUsername(String name) throws NoSuchMemberException;
	
	/**
	 *  Returns loanable items.
	 *  
	 *  @param name the name of the title
	 *  @return returns loanable copies else empty list
	 */
	List<TitleCopy> getLoanableTitleCopiesByName(String name);
	
	/**
	 *  Returns loanable items.
	 *  
	 *  @param name the name of the title
	 *  @param clazz the filter by type class
	 *  
	 *  @return returns loanable copies else empty list
	 */
	<T> List<T> getLoanableTitleCopiesByNameAndType(String name, Class<T> clazz);

	void addItemToInventory(Title item);
	
	void addMember(Member member);

	List<Title> getLoanableTitles();

	Title getTitleByName(String name);

	List<TitleCopy> getOverdueItems();
}
