package com.airship.tags.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.airship.tags.domain.UserTagEntity;
import com.airship.tags.rest.domain.UserTagRequest;
import com.airship.tags.rest.domain.UserTagResponse;

@Service
public interface UserTagService {

	public UserTagResponse pushTag(UserTagRequest tagRequest);
		
	public Set<UserTagEntity> findAllUserTagEntitiesByUserId(String userId);
}
