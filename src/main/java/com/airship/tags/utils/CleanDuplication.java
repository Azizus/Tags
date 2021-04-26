package com.airship.tags.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class CleanDuplication {
	
	public Map<String, Set<String>> cleanSetsDuplication (Set<String> add, Set<String> remove){
		
		HashSet<String> intermerdiate = new HashSet<String>(remove);
		intermerdiate.retainAll(add);
		remove.removeAll(intermerdiate);
		add.removeAll(intermerdiate);
		
		HashMap<String, Set<String>> cleaned = new HashMap<String, Set<String>>();
		cleaned.put("add", add);
		cleaned.put("remove", remove);
		return cleaned;
	}

}
