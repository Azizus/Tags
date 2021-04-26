package com.airship.tags.repository;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.airship.tags.domain.UserTagEntity;


@Repository
public class UserTagRepository {
	
	public UserTagEntity save(UserTagEntity tagEntity) {
		
		DataStore.tags.add(tagEntity);
		
		return tagEntity;
	}

	public Set<UserTagEntity> getByUserId(String userId) {
	
		return DataStore.tags.stream()
			.filter(tag -> tag.getUserId().equals(userId))
			.collect(Collectors.toSet());
	}
	
}
