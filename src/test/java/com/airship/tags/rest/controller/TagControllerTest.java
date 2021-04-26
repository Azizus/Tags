package com.airship.tags.rest.controller;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.airship.tags.rest.domain.UserTagRequest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class TagControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void request_response_should_not_be_null_when_posting_a_user_tag() throws Exception {

		Set<String> add = new HashSet<>();
		Set<String> remove = new HashSet<>();
		UserTagRequest tagRequest = new UserTagRequest("1", add, remove, "2018-08-10T06:49:04.360Z");

		assertNotNull(testRestTemplate.postForObject("http://localhost:1917/api/tags", tagRequest, UserTagRequest.class));
	}

//	@Test
//	public void request_response_should_return_user_id_and_user_tags_when_posting_a_user_tag() throws Exception {
//
//		Set<String> add = new HashSet<>();
//		Set<String> remove = new HashSet<>();
//		UserTagRequest tagRequest = new UserTagRequest("1", add, remove, LocalDateTime.now());
//
//		assertNotNull(testRestTemplate.postForObject("http://localhost:1917/api/tags", tagRequest, UserTagRequest.class));
//	}
}
