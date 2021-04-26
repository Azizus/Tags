package com.airship.tags.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airship.tags.rest.domain.UserTagRequest;
import com.airship.tags.rest.domain.UserTagResponse;
import com.airship.tags.service.UserTagService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/tags")
public class TagController {

	@Autowired
	private UserTagService tagService;
	
	@PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(method = "POST", summary = "Push Tag", description = "Push user tag")
	public UserTagResponse push(@Valid @RequestBody UserTagRequest userTagRequest) {
		
		return tagService.pushTag(userTagRequest);
	}
	
}
