package org.vicomtech.opener.multiwords.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CorpusHandlingUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

//	@Test
//	public void testGetFilesFromDir() {
//		fail("Not yet implemented");
//	}

//	@Test
//	public void testWriteTextsToFiles() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGenerateUniqueFileName() {
		String baseFilePath="multiwords.txt";
		String result=CorpusHandlingUtils.generateUniqueFileName(baseFilePath);
		System.out.println(result);
		assertEquals("C:\\Users\\agarciap\\repositories\\research\\multiwords\\multiwords_20140624.txt".toLowerCase(), result.toLowerCase());
		
	}

	@Test
	public void testGenerateUniqueFileName2() {
		String baseFilePath="C:\\some_folder\\some_other_folder\\multiwords.txt";
		String result=CorpusHandlingUtils.generateUniqueFileName(baseFilePath);
		System.out.println(result);
		assertEquals("C:\\some_folder\\some_other_folder\\multiwords_20140624.txt".toLowerCase(), result.toLowerCase());
		
	}
}
