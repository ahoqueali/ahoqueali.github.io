package org.ahoque;

import java.util.Collection;

public interface Title {

	public String getTitle();
	
	public void add(TitleCopy copy);
	
	public Collection<TitleCopy> getLoanableCopies();
}
