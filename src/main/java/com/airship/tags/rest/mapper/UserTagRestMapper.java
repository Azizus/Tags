package com.airship.tags.rest.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.airship.tags.domain.UserTagEntity;
import com.airship.tags.domain.data.Action;
import com.airship.tags.domain.data.Tag;
import com.airship.tags.rest.domain.UserTagRequest;
import com.airship.tags.rest.domain.UserTagResponse;
import com.airship.tags.utils.ActionEnum;

@Component
public class UserTagRestMapper {

	public UserTagResponse UserTagEntityToUserTagResponse(UserTagEntity tagEntity) {
		return new UserTagResponse(tagEntity.getUserId(), tagEntity.getTags());
	}

	public UserTagEntity UserTagRequestToUserTagEntity(UserTagRequest userTagRequest) {
		UserTagEntity tagEntity = new UserTagEntity();

		Set<Tag> tags = new HashSet<>();

		tags.addAll(this.createAddListOfTagFromUserTagRequest(userTagRequest));
		tags.addAll(this.createRemoveListOfTagFromUserTagRequest(userTagRequest));

		tagEntity.setUserId(userTagRequest.getUser());
		tagEntity.setTags(tags);

		return tagEntity;

	}

	public List<Action> createListOfActionFromUserTagRequest(UserTagRequest userTagRequest) {
		Action action = new Action();
		List<Action> actions = new ArrayList<>();

		action.setTimestamp(userTagRequest.getTimestamp());
		action.setAction(ActionEnum.ADD);
		actions.add(action);

		return actions;

	}

	public Set<Tag> createAddListOfTagFromUserTagRequest(UserTagRequest userTagRequest) {

		Set<Tag> tagToAddList = new HashSet<>();
		for (String add : userTagRequest.getAdd()) {
			Tag tag = new Tag();

			tag.setTag(add);
			tag.setActions(this.createListOfActionFromUserTagRequest(userTagRequest));
			tagToAddList.add(tag);
		}
		return tagToAddList;
	}

	public Set<Tag> createRemoveListOfTagFromUserTagRequest(UserTagRequest userTagRequest) {

		Set<Tag> tagToRemoveList = new HashSet<>();
		for (String remove : userTagRequest.getAdd()) {
			Tag tag = new Tag();

			tag.setTag(remove);
			tag.setActions(this.createListOfActionFromUserTagRequest(userTagRequest));
			tagToRemoveList.add(tag);
		}
		return tagToRemoveList;
	}
}
