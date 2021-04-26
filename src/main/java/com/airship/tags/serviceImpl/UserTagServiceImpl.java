package com.airship.tags.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airship.tags.domain.UserTagEntity;
import com.airship.tags.domain.data.Tag;
import com.airship.tags.repository.UserTagRepository;
import com.airship.tags.rest.domain.UserTagRequest;
import com.airship.tags.rest.domain.UserTagResponse;
import com.airship.tags.rest.mapper.UserTagRestMapper;
import com.airship.tags.service.UserTagService;
import com.airship.tags.utils.CleanDuplication;

@Service
public class UserTagServiceImpl implements UserTagService {

	@Autowired
	private UserTagRepository userTagRepository;

	@Autowired
	private UserTagRestMapper userTagRestMapper;

	@Autowired
	private CleanDuplication cleanDuplication;

	@Override
	public UserTagEntity findTagEntitybyUserId(String userId) {

		return userTagRepository.findTagEntityByUserId(userId);
	}

	@Override
	public UserTagResponse pushTag(UserTagRequest userTagRequest) {

		UserTagEntity userTagEntity = this.findTagEntitybyUserId(userTagRequest.getUser());

		userTagRequest = this.cleanAddAndRemoveTags(userTagRequest);

		if (userTagEntity.getUserId() != null) {

			Set<Tag> tags = new HashSet<>();
			// creating add actions from add list
			tags.addAll(userTagRestMapper.createAddListOfTagFromUserTagRequest(userTagRequest));

			// creating remove actions from remove list
			tags.addAll(userTagRestMapper.createRemoveListOfTagFromUserTagRequest(userTagRequest));

			if (userTagEntity.getTags() == null)
				userTagEntity.setTags(tags);
			else {
				Set<Tag> combinedTags = new HashSet<>();
				combinedTags.addAll(userTagEntity.getTags());
				combinedTags.addAll(tags);
				userTagEntity.setTags(combinedTags);
			}
			return userTagRestMapper.UserTagEntityToUserTagResponse(userTagRepository.save(userTagEntity));

		} else {
			userTagEntity = userTagRestMapper.UserTagRequestToUserTagEntity(userTagRequest);
			return userTagRestMapper.UserTagEntityToUserTagResponse(userTagRepository.save(userTagEntity));
		}
	}

	private UserTagRequest cleanAddAndRemoveTags(UserTagRequest userTagRequest) {

		Map<String, Set<String>> cleaned = new HashMap<>();

		cleaned = cleanDuplication.cleanSetsDuplication(userTagRequest.getAdd(), userTagRequest.getRemove());

		userTagRequest.setAdd(cleaned.get("add"));
		userTagRequest.setRemove(cleaned.get("remove"));

		return userTagRequest;
	}

}
