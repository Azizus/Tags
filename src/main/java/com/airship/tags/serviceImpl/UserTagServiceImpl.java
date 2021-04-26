package com.airship.tags.serviceImpl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airship.tags.domain.UserTagEntity;
import com.airship.tags.repository.UserTagRepository;
import com.airship.tags.rest.domain.UserTagRequest;
import com.airship.tags.rest.domain.UserTagResponse;
import com.airship.tags.rest.mapper.UserTagRestMapper;
import com.airship.tags.service.UserTagService;

@Service
public class UserTagServiceImpl implements UserTagService {

	@Autowired
	private UserTagRepository tagRepository;

	@Autowired
	private UserTagRestMapper tagRequestMapper;

	@Override
	public UserTagEntity findTagEntitybyUserId(Long userId) {

		return tagRepository.findTagEntityByUserId(userId);
	}

	@Override
	public UserTagResponse pushTag(UserTagRequest tagRequest) {

		UserTagEntity tagEntity = this.findTagEntitybyUserId(tagRequest.getUserId());

		if (tagEntity.getUserId() != null) {
			if (tagEntity.getTags() == null) {
				tagRequest = this.cleanAddAndRemoveTags(tagRequest);
//				tagEntity = this.tagListInit(tagEntity, tagRequest);
				tagEntity.setTags(new HashSet<>());

			}

			return tagRequestMapper.TagEntityToTagResponse(tagRepository.save(tagEntity));

		} else {
			tagRequest = this.cleanAddAndRemoveTags(tagRequest);
			tagEntity = tagRequestMapper.TagRequestToTagEntity(tagRequest);
			return tagRequestMapper.TagEntityToTagResponse(tagRepository.save(tagEntity));
		}
	}

	private UserTagEntity tagListInit(UserTagEntity tagEntity, UserTagRequest tagRequest) {
		tagEntity.setTags(new HashSet<>());
		tagEntity.getTags().addAll(tagRequest.getAdd());
		tagEntity.getTags().removeAll(tagRequest.getRemove());
		return tagEntity;
	}

	private UserTagRequest cleanAddAndRemoveTags(UserTagRequest tagRequest) {
		HashSet<String> intermerdiate = new HashSet<>(tagRequest.getRemove());
		intermerdiate.retainAll(tagRequest.getAdd());
		tagRequest.getRemove().removeAll(intermerdiate);
		tagRequest.getAdd().removeAll(intermerdiate);
		return tagRequest;
	}

}
