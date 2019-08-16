package org.ahoque.library.service;

import org.ahoque.library.domain.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class LibraryService implements Library {

	private final Map<String, Member> members = new ConcurrentHashMap<>();
	private Inventory inventory = new InventoryService();
	
	@Override
	public Member getMemberByUsername(String username) throws NoSuchMemberException {
		
		if(!members.containsKey(username)) {
			throw new NoSuchMemberException("Member does not exist!");
		}
		
		return members.get(username);
	}

	/**
	 *  Returns loanable items.
	 *  
	 *  @param name the name of the title
	 *  @return returns loanable copies else empty list
	 */
	@Override
	public List<TitleCopy> getLoanableTitleCopiesByName(String name) {
		return inventory.getLoanableTitles().stream()
				.filter(title -> title.getTitle().equals(name))
				.flatMap(title -> title.getLoanableItems().stream())
				.collect(Collectors.toList());
	}
	

	/**
	 *  Returns loanable items.
	 *  
	 *  @param name the name of the title
	 *  @param clazz the filter by type class
	 *  
	 *  @return returns loanable copies else empty list
	 */
	@Override
	public <T> List<T> getLoanableTitleCopiesByNameAndType(String name, Class<T> clazz) {
		return inventory.getLoanableTitles().stream()
				.filter(title -> title.getTitle().equals(name))
				.flatMap(title -> title.getLoanableItems().stream())
				.filter(clazz::isInstance)
				.map(copy -> (T)copy)
				.collect(Collectors.toList());
	}

	@Override
	public void addItemToInventory(Title item) {
		inventory.addTitle(item);
	}

	@Override
	public void addMember(Member member) {
		members.put(member.getUsername(), member);
	}

	@Override
	public List<Title> getLoanableTitles() {
		return inventory.getLoanableTitles(); 
	}

	@Override
	public Title getTitleByName(String name) {
		return inventory.getTitleByName(name);
	}

	@Override
	public List<TitleCopy> getOverdueItems() {
		return inventory.getOverdueTitles().stream()
				.flatMap(title -> title.getOverdueItems().stream())
				.collect(Collectors.toList());
	}
}
