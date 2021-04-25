package com.airship.tags.rest.mapper;

import java.util.HashSet;

import org.springframework.stereotype.Component;

import com.airship.tags.domain.TagEntity;
import com.airship.tags.rest.domain.TagRequest;
import com.airship.tags.rest.domain.TagResponse;

@Component
public class TagRestMapper {

	public TagResponse TagEntityToTagResponse(TagEntity tagEntity) {
		return new TagResponse(tagEntity.getUserId(), tagEntity.getTags());
	}

	public TagEntity TagRequestToTagEntity(TagRequest tagRequest) {
		TagEntity tagEntity = new TagEntity();
		
		tagEntity.setUserId(tagRequest.getUserId());
		tagEntity.setTags(new HashSet<>());
		tagEntity.getTags().addAll(tagRequest.getAdd());
		tagEntity.getTags().removeAll(tagRequest.getRemove());
		tagEntity.setDate(tagRequest.getDate());
		return tagEntity;
	}
}
