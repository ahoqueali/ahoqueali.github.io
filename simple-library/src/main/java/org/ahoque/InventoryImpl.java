package org.ahoque;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InventoryImpl implements Inventory {

	private Map<String, Title> titles = new ConcurrentHashMap<>();
	
	@Override
	public List<Title> getLoanableTitles() {
		return titles.values().stream()
				.filter(title -> title.getLoanableCopies().size() > 0)
				.collect(Collectors.toList());
	}

	@Override
	public void add(Title item) {
		titles.put(item.getTitle(), item);
	}
}
