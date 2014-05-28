package org.vicomtech.opener.multiwords.preprocess;

import ixa.kaflib.KAFDocument;
import ixa.kaflib.Term;
import ixa.kaflib.WF;

import java.io.StringReader;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.collect.Lists;

public class KafBasedMultiwordDocumentPreprocessor implements DocumentPreprocessor{

	private static Logger log=Logger.getLogger(KafBasedMultiwordDocumentPreprocessor.class);
	
	@Override
	public List<String> preprocessDocument(String content) {
			List<String> preprocessedResults = getPreprocessedContent(content);
			return preprocessedResults;
	}
	
	/**
	 * Get a list of String, one per sentence, with the format lemma1_pos1 lemma2_pos2 ... lemmaN_posN
	 * @param kaf
	 * @return
	 */
	protected List<String> getPreprocessedContent(String kaf){
		List<String>results=Lists.newArrayList();
		try{
			KAFDocument kafdoc=KAFDocument.createFromStream(new StringReader(kaf));
			List<List<WF>> sentences = kafdoc.getSentences();
			for(List<WF> sentence:sentences){
				StringBuffer sb=new StringBuffer();
				int sentenceNumber=sentence.get(0).getSent();
				List<Term> sentenceTerms = kafdoc.getSentenceTerms(sentenceNumber);
				for(Term term:sentenceTerms){
						sb.append(term.getLemma());
						sb.append("_");
						sb.append(term.getPos());
						sb.append(" ");
				}
				results.add(sb.toString().trim());
			}
			return results;
		}catch(Exception e){
			log.warn(e);
			return results;
		}
	}

}
