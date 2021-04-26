package com.airship.tags.serviceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airship.tags.domain.UserTagEntity;
import com.airship.tags.repository.UserTagRepository;
import com.airship.tags.rest.domain.UserTagRequest;
import com.airship.tags.rest.domain.UserTagResponse;
import com.airship.tags.rest.mapper.UserTagRestMapper;
import com.airship.tags.service.UserTagService;
import com.airship.tags.utils.ActionEnum;
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
	public UserTagResponse pushTag(UserTagRequest userTagRequest) {

		userTagRequest = this.cleanAddAndRemoveTags(userTagRequest);

		for (String add : userTagRequest.getAdd()) {
			UserTagEntity userTagEntity = new UserTagEntity();
			userTagEntity = userTagRestMapper.UserTagRequestToUserTagEntity(userTagRequest, add, ActionEnum.ADD);
			userTagRepository.save(userTagEntity);
		}

		for (String remove : userTagRequest.getRemove()) {
			UserTagEntity userTagEntity = new UserTagEntity();
			userTagEntity = userTagRestMapper.UserTagRequestToUserTagEntity(userTagRequest, remove, ActionEnum.REMOVE);
			userTagRepository.save(userTagEntity);
		}

		return this.findAllUserTags(userTagRequest.getUser());
	}

	private UserTagRequest cleanAddAndRemoveTags(UserTagRequest userTagRequest) {

		Map<String, Set<String>> cleaned = new HashMap<>();

		cleaned = cleanDuplication.cleanSetsDuplication(userTagRequest.getAdd(), userTagRequest.getRemove());

		userTagRequest.setAdd(cleaned.get("add"));
		userTagRequest.setRemove(cleaned.get("remove"));

		return userTagRequest;
	}

	@Override
	public UserTagResponse findAllUserTags(String userId) {
		return new UserTagResponse();
	}

}
