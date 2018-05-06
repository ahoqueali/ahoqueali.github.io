package org.ahoque;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InventoryImpl implements Inventory {

	// Using concurrent hash map for performance and thread safety when doing read, write and updates
	private final Map<String, Title> titles = new ConcurrentHashMap<>();
	
	@Override
	public List<Title> getLoanableTitles() {
		return titles.values().stream()
				.filter(title -> title.getLoanableCopies().size() > 0)
				.collect(Collectors.toList());
	}

	@Override
	public void addTitle(Title item) {
		titles.put(item.getTitle(), item);
	}

	@Override
	public List<Title> getOverdueTitles() {
		return titles.values().stream()
				.filter(title -> title.getOverdueCopies().size() > 0)
				.collect(Collectors.toList());
	}

	@Override
	public Title getTitleByName(String name) {
		return titles.get(name);
	}
}
