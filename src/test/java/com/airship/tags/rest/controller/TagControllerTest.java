package com.airship.tags.rest.controller;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.airship.tags.rest.domain.TagRequest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class TagControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void request_response_should_not_be_null_when_pushing_a_tag() throws Exception {

		List<String> add = new ArrayList<String>();
		List<String> remove = new ArrayList<String>();
		TagRequest tagRequest = new TagRequest(1L, add, remove, LocalDateTime.now());

		assertNotNull(testRestTemplate.postForObject("http://localhost:1917/api/tags", tagRequest, TagRequest.class));
	}

}
