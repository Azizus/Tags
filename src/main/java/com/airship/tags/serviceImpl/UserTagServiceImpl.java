package com.airship.tags.serviceImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airship.tags.domain.UserTagEntity;
import com.airship.tags.domain.nonpersistant.Tag;
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

		if (userTagEntity.getUserId() != null) {
			if (userTagEntity.getTags() == null) {
				userTagRequest = this.cleanAddAndRemoveTags(userTagRequest);
				
				Set<Tag> tags = new HashSet<>();
				//creating add actions from add list
				tags.addAll(userTagRestMapper.createAddListOfTagFromTagRequest(userTagRequest));
				
				//creating remove actions from remove list
				tags.addAll(userTagRestMapper.createRemoveListOfTagFromTagRequest(userTagRequest));			
				
				userTagEntity.setTags(tags);

			}

			return userTagRestMapper.TagEntityToTagResponse(userTagRepository.save(userTagEntity));

		} else {
			userTagRequest = this.cleanAddAndRemoveTags(userTagRequest);
			userTagEntity = userTagRestMapper.TagRequestToTagEntity(userTagRequest);
			return userTagRestMapper.TagEntityToTagResponse(userTagRepository.save(userTagEntity));
		}
	}

	private UserTagRequest cleanAddAndRemoveTags(UserTagRequest tagRequest) {
		
		Map<String, Set<String>> cleaned = new HashMap<>();
		
		cleaned = cleanDuplication.cleanSetsDuplication(tagRequest.getAdd(), tagRequest.getRemove());
		
		tagRequest.setAdd(cleaned.get("first"));
		tagRequest.setRemove(cleaned.get("second"));
		
		return tagRequest;
	}

}
