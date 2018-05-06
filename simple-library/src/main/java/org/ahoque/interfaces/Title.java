package org.ahoque.interfaces;

import java.util.List;

public interface Title {

	public String getTitle();
	
	public void addTitleCopy(TitleCopy copy);
	
	public List<TitleCopy> getLoanableCopies();

	public List<TitleCopy> getOverdueCopies();
}
