package com.airship.tags.rest.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.airship.tags.domain.UserTagEntity;
import com.airship.tags.domain.nonpersistant.Action;
import com.airship.tags.domain.nonpersistant.Tag;
import com.airship.tags.rest.domain.UserTagRequest;
import com.airship.tags.rest.domain.UserTagResponse;
import com.airship.tags.utils.ActionEnum;

@Component
public class UserTagRestMapper {

	public UserTagResponse TagEntityToTagResponse(UserTagEntity tagEntity) {
		return new UserTagResponse(tagEntity.getUserId(), tagEntity.getTags());
	}

	public UserTagEntity TagRequestToTagEntity(UserTagRequest userTagRequest) {
		UserTagEntity tagEntity = new UserTagEntity();

		Set<Tag> tags = new HashSet<>();

		tags.addAll(this.createAddListOfTagFromTagRequest(userTagRequest));
		tags.addAll(this.createRemoveListOfTagFromTagRequest(userTagRequest));

		tagEntity.setUserId(userTagRequest.getUser());
		tagEntity.setTags(tags);

		return tagEntity;

	}

	public List<Action> createListOfActionFromTagRequest(UserTagRequest userTagRequest) {
		Action action = new Action();
		List<Action> actions = new ArrayList<>();

		action.setTimestamp(userTagRequest.getTimestamp());
		action.setAction(ActionEnum.ADD.toString());
		actions.add(action);

		return actions;

	}

	public Set<Tag> createAddListOfTagFromTagRequest(UserTagRequest userTagRequest) {

		Set<Tag> tagToAddList = new HashSet<>();
		for (String add : userTagRequest.getAdd()) {
			Tag tag = new Tag();

			tag.setTag(add);
			tag.setActions(this.createListOfActionFromTagRequest(userTagRequest));
			tagToAddList.add(tag);
		}
		return tagToAddList;
	}

	public Set<Tag> createRemoveListOfTagFromTagRequest(UserTagRequest userTagRequest) {

		Set<Tag> tagToRemoveList = new HashSet<>();
		for (String remove : userTagRequest.getAdd()) {
			Tag tag = new Tag();

			tag.setTag(remove);
			tag.setActions(this.createListOfActionFromTagRequest(userTagRequest));
			tagToRemoveList.add(tag);
		}
		return tagToRemoveList;
	}
}
