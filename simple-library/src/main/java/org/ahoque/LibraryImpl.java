package org.ahoque;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LibraryImpl implements Library {

	private Set<Member> members;
	private Inventory inventory;
	
	public LibraryImpl() {
		members = new HashSet<>();
		inventory = new InventoryImpl();
	}

	@Override
	public Member getMemberByUsername(String name) {
		return members.stream()
				.filter(user -> user.getName().equals(name)).collect(Collectors.toList()).get(0);
	}

	/**
	 *  Returns loanable title copies.
	 *  @param name the name of the title
	 *  @return loanable copies or empty list
	 */
	@Override
	public List<TitleCopy> findTileCopiesByName(String name) {
		
		return inventory.getLoanableTitles().stream()
				.filter(title -> title.getTitle().equals(name))
				.flatMap(title -> title.getLoanableCopies().stream())
				.collect(Collectors.toList());
	}

	@Override
	public void addItemToInventory(Title item) {
		inventory.add(item);
	}

	@Override
	public void addMember(Member member) {
		members.add(member);
	}
	
	
}
