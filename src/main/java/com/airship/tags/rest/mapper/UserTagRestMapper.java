package com.airship.tags.rest.mapper;

import org.springframework.stereotype.Component;

import com.airship.tags.domain.UserTagEntity;
import com.airship.tags.rest.domain.UserTagRequest;
import com.airship.tags.utils.ActionEnum;

@Component
public class UserTagRestMapper {

	public UserTagEntity UserTagRequestToUserTagEntity(UserTagRequest userTagRequest, String tag, ActionEnum action) {

		UserTagEntity tagEntity = new UserTagEntity();

		tagEntity.setUserId(userTagRequest.getUser());
		tagEntity.setTag(tag);
		tagEntity.setAction(action);
		tagEntity.setTimestamp(userTagRequest.getTimestamp());

		return tagEntity;

	}

}
