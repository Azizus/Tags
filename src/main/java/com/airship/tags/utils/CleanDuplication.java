package com.airship.tags.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class CleanDuplication {
	
	public Map<String, Set<String>> cleanSetsDuplication (Set<String> firstList, Set<String> secondList){
		
		HashSet<String> intermerdiate = new HashSet<String>(secondList);
		intermerdiate.retainAll(firstList);
		secondList.removeAll(intermerdiate);
		firstList.removeAll(intermerdiate);
		
		HashMap<String, Set<String>> cleaned = new HashMap<String, Set<String>>();
		cleaned.put("first", firstList);
		cleaned.put("second", secondList);
		return cleaned;
	}

}
