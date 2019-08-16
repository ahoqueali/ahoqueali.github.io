package org.ahoque.library.service;

import org.ahoque.library.domain.Inventory;
import org.ahoque.library.domain.Title;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InventoryService implements Inventory {

	private final Map<String, Title> titles = new ConcurrentHashMap<>();
	
	@Override
	public List<Title> getLoanableTitles() {
		return titles.values().stream()
				.filter(title -> title.getLoanableItems().size() > 0)
				.collect(Collectors.toList());
	}

	@Override
	public void addTitle(Title item) {
		titles.put(item.getTitle(), item);
	}

	@Override
	public List<Title> getOverdueTitles() {
		return titles.values().stream()
				.filter(title -> title.getOverdueItems().size() > 0)
				.collect(Collectors.toList());
	}

	@Override
	public Title getTitleByName(String name) {
		return titles.get(name);
	}
}
