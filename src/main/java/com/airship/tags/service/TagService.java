package com.airship.tags.service;

import org.springframework.stereotype.Service;

import com.airship.tags.domain.TagEntity;
import com.airship.tags.rest.domain.TagRequest;
import com.airship.tags.rest.domain.TagResponse;

@Service
public interface TagService {

	public TagResponse pushTag(TagRequest tagRequest);
	
	public TagEntity findTagEntitybyUserId(Long userId);
		
}
