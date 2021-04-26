package com.airship.tags.repository;


import org.springframework.stereotype.Repository;

import com.airship.tags.domain.UserTagEntity;


@Repository
public class TagRepository {
	
	public UserTagEntity save(UserTagEntity tagEntity) {
		
		DataStore.tags.add(tagEntity);
		
		return tagEntity;
	}

	public UserTagEntity findTagEntityByUserId(Long userId) {
		
		UserTagEntity emptyTagEntity = new UserTagEntity();
		
		for (UserTagEntity tagEntity: DataStore.tags) {
			if (tagEntity.getUserId().equals(userId))
				return tagEntity;
		}
		return emptyTagEntity;
	}
	
}
