package org.vicomtech.opener.multiwords.utils;

import java.util.HashMap;

import org.vicomtech.opener.multiwords.model.PartOfSpeech;

import static org.vicomtech.opener.multiwords.model.PartOfSpeech.*;


public class KafTagsetMapper extends AbstractTagsetMapper{

	@Override
	protected void loadMappings() {
		this.postagsMap=new HashMap<PartOfSpeech,String>();
		postagsMap.put(ADJECTIVE, "G");
		postagsMap.put(NOUN, "N");
		postagsMap.put(ADVERB, "A");
		postagsMap.put(VERB, "V");
		
		//MISSING DEPENDENCY TAGMAPPING...
		
	}

}
