package org.vicomtech.opener.multiwords.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;
import org.vicomtech.opener.multiwords.model.NGramAndScore;
import org.vicomtech.opener.multiwords.utils.CorpusHandlingUtils;

public class CLI {

	private static Options options=loadOptions();
	
	@SuppressWarnings("static-access")
	private static Options loadOptions(){
		Options options=new Options();
		Option inputDir=OptionBuilder.hasArg(true).isRequired(true).withDescription("Input corpus path").create("i");
		Option ngramSize=OptionBuilder.hasArg(true).withDescription("Maximum length of the generated multiwords").create("n");
		Option listSize=OptionBuilder.hasArg(true).withDescription("Length of the (ordered) multiword list").create("l");
		Option outputFile=OptionBuilder.hasArg(true).withDescription("Output file to write multiword list").create("o");
		
		options.addOption(inputDir);
		options.addOption(ngramSize);
		options.addOption(listSize);
		options.addOption(outputFile);
		
		return options;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(System.console()==null){
			args=new String[]{"-i","C:\\Users\\agarciap\\Data\\REVIEWS_DATA\\EN_REVIEWS_KAF",
					};
		}
		execute(args);
	}

	public static void execute(String[]args){
		CommandLineParser parser=new BasicParser();
		try{
			CommandLine line=parser.parse(options, args);
			String inputDirPath=line.getOptionValue("i");
			String outputFilePath=returnUserValueOrDefault(line, "o", "generated-multiwords.txt");
			int ngramSize=returnUserValueOrDefault(line, "n", 2);
			int listSize=returnUserValueOrDefault(line, "l", 100);
			
			MultiwordGenerator multiwordGenerator=new MultiwordGenerator();
			List<NGramAndScore> rankedGrams = multiwordGenerator.generateMultiwords(inputDirPath, ngramSize, listSize, outputFilePath);
			List<String>plainList=NGramAndScore.toPlainMultiwordList(rankedGrams);
			File outputfile=new File(CorpusHandlingUtils.generateUniqueFileName(outputFilePath));
			try {
				FileUtils.writeLines(outputfile,"UTF-8", plainList);
				System.out.println("Multiword terms written to: "+outputfile.getAbsolutePath());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
			HelpFormatter formatter = new HelpFormatter();
        	formatter.printHelp( "java -jar NAME_OF_THE_JAR [OPTIONS ...]", options );
		}
	}
	
	protected static Integer returnUserValueOrDefault(CommandLine line,String param,Integer defaultValue){
		if(line.hasOption(param)){
			return Integer.parseInt(line.getOptionValue(param));
		}else{
			return defaultValue;
		}
	}
	
	protected static String returnUserValueOrDefault(CommandLine line,String param,String defaultValue){
		if(line.hasOption(param)){
			return line.getOptionValue(param);
		}else{
			return defaultValue;
		}
	}
}
