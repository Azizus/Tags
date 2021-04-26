package com.airship.tags.rest.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.airship.tags.domain.UserTagEntity;
import com.airship.tags.rest.domain.UserTagRequest;
import com.airship.tags.utils.ActionEnum;
import com.airship.tags.utils.DateUtils;

@Component
public class UserTagRestMapper {

	public UserTagEntity UserTagRequestToUserTagEntity(String user, LocalDateTime timestamp, String tag, ActionEnum action) {

		UserTagEntity tagEntity = new UserTagEntity();

		tagEntity.setUserId(user);
		tagEntity.setTag(tag);
		tagEntity.setAction(action);
		tagEntity.setTimestamp(timestamp);

		return tagEntity;

	}

}
