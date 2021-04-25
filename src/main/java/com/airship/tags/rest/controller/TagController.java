package com.airship.tags.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airship.tags.rest.domain.TagRequest;
import com.airship.tags.rest.domain.TagResponse;
import com.airship.tags.service.TagService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/tags")
public class TagController {

	@Autowired
	private TagService tagService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(method = "POST", summary = "Push Tag", description = "Push tag with specific informations")
	public TagResponse push(@Valid @RequestBody TagRequest tagRequest) {
		
		return tagService.pushTag(tagRequest);
	}
	
}
