package com.airship.tags.rest.mapper;

import java.util.HashSet;

import org.springframework.stereotype.Component;

import com.airship.tags.domain.UserTagEntity;
import com.airship.tags.rest.domain.TagRequest;
import com.airship.tags.rest.domain.TagResponse;

@Component
public class TagRestMapper {

	public TagResponse TagEntityToTagResponse(UserTagEntity tagEntity) {
		return new TagResponse(tagEntity.getUserId(), tagEntity.getTags());
	}

	public UserTagEntity TagRequestToTagEntity(TagRequest tagRequest) {
		UserTagEntity tagEntity = new UserTagEntity();
		
		tagEntity.setUserId(tagRequest.getUserId());
		tagEntity.setTags(new HashSet<>());
		tagEntity.getTags().addAll(tagRequest.getAdd());
		tagEntity.getTags().removeAll(tagRequest.getRemove());
		tagEntity.setDate(tagRequest.getDate());
		return tagEntity;
	}
}
