package com.airship.tags.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airship.tags.rest.domain.TagRequest;
import com.airship.tags.rest.domain.TagResponse;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/tags")
public class TagController {

	
	@PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(method = "POST", summary = "Push Tag", description = "Push tag with specific informations")
	public TagResponse push(@Valid @RequestBody TagRequest tagRequest) {
	
		List<String> add = new ArrayList<String>();
		List<String> remove = new ArrayList<String>();
	
		return new TagResponse(1L, add, remove, new Date());
	}
	
}
