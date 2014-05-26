package org.vicomtech.opener.multiwords;

import java.util.List;

public interface DocumentPreprocessor {

	public List<String> preprocessDocument(String content, String language,boolean isKaf);
	
}
