package com.airship.tags.repository;


import org.springframework.stereotype.Repository;

import com.airship.tags.domain.UserTagEntity;


@Repository
public class UserTagRepository {
	
	public UserTagEntity save(UserTagEntity tagEntity) {
		
		DataStore.tags.add(tagEntity);
		
		return tagEntity;
	}
	
}
