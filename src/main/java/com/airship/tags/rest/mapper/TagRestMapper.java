package com.airship.tags.rest.mapper;

import org.springframework.stereotype.Component;

import com.airship.tags.domain.TagEntity;
import com.airship.tags.rest.domain.TagResponse;

@Component
public class TagRestMapper {

	public TagResponse TagEntityToTagResponse(TagEntity tagEntity) {
		return new TagResponse(tagEntity.getUserId(), tagEntity.getTags());
	}
}
