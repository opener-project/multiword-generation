package org.vicomtech.opener.multiwords.preprocess;

import java.util.List;

public interface DocumentPreprocessor {

	public List<String> preprocessDocument(String content);
	
}
