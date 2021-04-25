package com.airship.tags.repository;


import org.springframework.stereotype.Repository;

import com.airship.tags.domain.TagEntity;

@Repository
public class TagRepository {
	
	public TagEntity save(TagEntity tagEntity) {
		
		DataStore.tags.add(tagEntity);
		
		return tagEntity;
	}

}
