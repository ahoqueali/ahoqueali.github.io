package org.ahoque.library.domain;

import java.util.List;

public interface Title {

	public String getTitle();
	
	public void addTitleCopy(TitleCopy item);
	
	public List<TitleCopy> getLoanableItems();

	public List<TitleCopy> getOverdueItems();
}
