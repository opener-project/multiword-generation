package org.vicomtech.opener.multiwords.data;

import java.util.List;

public interface CorpusReader {

	public String readCorpusFileContent(String corpusFilePath);
	
	public List<String> readCorpusDirectoryFileContent(String dirPath);
	
}
