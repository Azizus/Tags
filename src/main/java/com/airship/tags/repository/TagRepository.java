package com.airship.tags.repository;


import org.springframework.stereotype.Repository;

import com.airship.tags.domain.TagEntity;


@Repository
public class TagRepository {
	
	public TagEntity save(TagEntity tagEntity) {
		
		DataStore.tags.add(tagEntity);
		
		return tagEntity;
	}

	public TagEntity findTagEntityByUserId(Long userId) {
		
		TagEntity emptyTagEntity = new TagEntity();
		
		for (TagEntity tagEntity: DataStore.tags) {
		if (tagEntity.getUserId().equals(userId))
			return tagEntity;
		}
		return emptyTagEntity;
	}
	
}
