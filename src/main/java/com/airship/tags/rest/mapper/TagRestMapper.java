package com.airship.tags.rest.mapper;

import java.util.Date;

import com.airship.tags.domain.TagEntity;
import com.airship.tags.rest.domain.TagRequest;
import com.airship.tags.rest.domain.TagResponse;



public class TagRestMapper {
	
	
	public TagEntity TagRequestToTag(TagRequest tagRequest) {
		return new TagEntity(
				tagRequest.getUserId(), tagRequest.getAdd(), tagRequest.getRemove(), new Date());	
	}

	public TagResponse TagEntityToTagResponse(TagEntity tagEntity) {
		return new TagResponse(
				tagEntity.getUserId(), tagEntity.getAdd(), tagEntity.getRemove(), tagEntity.getDate());
	}
}
