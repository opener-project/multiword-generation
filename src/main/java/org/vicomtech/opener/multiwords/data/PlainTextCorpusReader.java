package org.vicomtech.opener.multiwords.data;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.vicomtech.opener.multiwords.utils.CorpusHandlingUtils;

import com.google.common.collect.Lists;

public class PlainTextCorpusReader implements CorpusReader{

	@Override
	public String readCorpusFileContent(String pathToFile) {
		try {
			String fileContent=FileUtils.readFileToString(new File(pathToFile), "UTF-8");
			return fileContent;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<String> readCorpusDirectoryFileContent(String dirPath) {
		try {
			List<File> files = CorpusHandlingUtils.getFilesFromDir(dirPath);
			List<String>results=Lists.newArrayList();
			for(File corpusFile:files){
				String fileContent=FileUtils.readFileToString(corpusFile, "UTF-8");
				String[] fileContentLines = fileContent.split("\n");
				results.addAll(Arrays.asList(fileContentLines));
			}
			return results;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
