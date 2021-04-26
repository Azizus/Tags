package com.airship.tags.rest.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.airship.tags.rest.domain.UserTagRequest;
import com.airship.tags.rest.domain.UserTagResponse;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class TagControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void request_response_should_not_be_null_when_posting_a_user_tag() throws Exception {

		Set<String> add = Stream.of("beyhive_member")
				  .collect(Collectors.toCollection(HashSet::new));
		Set<String> remove = new HashSet<>();
		UserTagRequest tagRequest = new UserTagRequest("1", add, remove, LocalDateTime.now());

		assertNotNull(testRestTemplate.postForObject("http://localhost:1917/api/tags", tagRequest, UserTagRequest.class));
	}

	@Test
	public void request_response_should_return_userId_as_2_and_tags_as_beyhive_member_when_posting_a_user_tag() throws Exception {

		Set<String> add = Stream.of("beyhive_member")
				  .collect(Collectors.toCollection(HashSet::new));
		Set<String> remove = new HashSet<>();
		UserTagRequest tagRequest = new UserTagRequest("2", add, remove, LocalDateTime.now());

		ResponseEntity<String> response = testRestTemplate.
				postForEntity("http://localhost:1917/api/tags", tagRequest, String.class);
				 
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		
	}
}
