package com.airship.tags.rest.mapper;

import java.util.HashSet;

import org.springframework.stereotype.Component;

import com.airship.tags.domain.UserTagEntity;
import com.airship.tags.rest.domain.UserTagRequest;
import com.airship.tags.rest.domain.UserTagResponse;

@Component
public class UserTagRestMapper {

	public UserTagResponse TagEntityToTagResponse(UserTagEntity tagEntity) {
		return new UserTagResponse(tagEntity.getUserId(), tagEntity.getTags());
	}

	public UserTagEntity TagRequestToTagEntity(UserTagRequest tagRequest) {
		UserTagEntity tagEntity = new UserTagEntity();
		
		tagEntity.setUserId(tagRequest.getUserId());
		tagEntity.setTags(new HashSet<>());
		tagEntity.getTags().addAll(tagRequest.getAdd());
		tagEntity.getTags().removeAll(tagRequest.getRemove());
		tagEntity.setDate(tagRequest.getDate());
		return tagEntity;
	}
}
