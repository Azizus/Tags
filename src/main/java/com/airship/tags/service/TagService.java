package com.airship.tags.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.airship.tags.rest.domain.TagRequest;
import com.airship.tags.rest.domain.TagResponse;

@Service
public interface TagService {

	TagResponse pushTag(TagRequest tagRequest);

}
