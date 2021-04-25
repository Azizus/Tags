package com.airship.tags.serviceImpl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airship.tags.domain.TagEntity;
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
	public TagEntity findTagEntitybyUserId(Long userId) {

		return tagRepository.findTagEntityByUserId(userId);
	}

	@Override
	public TagResponse pushTag(TagRequest tagRequest) {

		TagEntity tagEntity = this.findTagEntitybyUserId(tagRequest.getUserId());
		if (tagEntity != null) {
			if (tagEntity.getTags() == null)
				tagEntity.setTags(new HashSet<>());

			tagEntity.getTags().addAll(tagRequest.getAdd());
			tagEntity.getTags().removeAll(tagRequest.getRemove());
			return tagRequestMapper.TagEntityToTagResponse(tagRepository.save(tagEntity));

		} else {
			return tagRequestMapper.TagEntityToTagResponse(tagRepository.save(tagEntity));
		}
	}

}
