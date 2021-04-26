package com.airship.tags.serviceImpl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airship.tags.domain.UserTagEntity;
import com.airship.tags.repository.TagRepository;
import com.airship.tags.rest.domain.TagRequest;
import com.airship.tags.rest.domain.TagResponse;
import com.airship.tags.rest.mapper.TagRestMapper;
import com.airship.tags.service.TagService;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private TagRestMapper tagRequestMapper;

	@Override
	public UserTagEntity findTagEntitybyUserId(Long userId) {

		return tagRepository.findTagEntityByUserId(userId);
	}

	@Override
	public TagResponse pushTag(TagRequest tagRequest) {

		UserTagEntity tagEntity = this.findTagEntitybyUserId(tagRequest.getUserId());

		if (tagEntity.getUserId() != null) {
			if (tagEntity.getTags() == null) {
				tagRequest = this.cleanAddAndRemoveTags(tagRequest);
				tagEntity = this.tagListInit(tagEntity, tagRequest);
			}

			return tagRequestMapper.TagEntityToTagResponse(tagRepository.save(tagEntity));

		} else {
			tagRequest = this.cleanAddAndRemoveTags(tagRequest);
			tagEntity = tagRequestMapper.TagRequestToTagEntity(tagRequest);
			return tagRequestMapper.TagEntityToTagResponse(tagRepository.save(tagEntity));
		}
	}

	private UserTagEntity tagListInit(UserTagEntity tagEntity, TagRequest tagRequest) {
		tagEntity.setTags(new HashSet<>());
		tagEntity.getTags().addAll(tagRequest.getAdd());
		tagEntity.getTags().removeAll(tagRequest.getRemove());
		return tagEntity;
	}

	private TagRequest cleanAddAndRemoveTags(TagRequest tagRequest) {
		HashSet<String> intermerdiate = new HashSet<>(tagRequest.getRemove());
		intermerdiate.retainAll(tagRequest.getAdd());
		tagRequest.getRemove().removeAll(intermerdiate);
		tagRequest.getAdd().removeAll(intermerdiate);
		return tagRequest;
	}

}
