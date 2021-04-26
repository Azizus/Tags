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
import com.airship.tags.utils.ActionEnum;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class TagControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

/*
 * test out of order
 */
	@Test
	public void test_out_of_order_removal_between_two_adds() throws Exception {

		UserTagRequest request = buildRequest("a", LocalDateTime.now(), ActionEnum.ADD);
		UserTagResponse expected = new UserTagResponse("1", Stream.of("a")
				  .collect(Collectors.toCollection(HashSet::new)));

		ResponseEntity<UserTagResponse> response = testRestTemplate.
				postForEntity("http://localhost:1917/api/tags", request, UserTagResponse.class);
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody(), equalTo(expected));
		
	
		// second push
		UserTagRequest request2 = buildRequest("a", LocalDateTime.now().minusHours(1), ActionEnum.ADD);
		UserTagResponse expected2 = new UserTagResponse("1", Stream.of("a")
				  .collect(Collectors.toCollection(HashSet::new)));

		ResponseEntity<UserTagResponse> response2 = testRestTemplate.
				postForEntity("http://localhost:1917/api/tags", request2, UserTagResponse.class);
		
		assertThat(response2.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response2.getBody(), equalTo(expected2));
		
	
		// third push
		UserTagRequest request3 = buildRequest("a", LocalDateTime.now().minusMinutes(30), ActionEnum.REMOVE);
		UserTagResponse expected3 = new UserTagResponse("1", Stream.of("a")
				  .collect(Collectors.toCollection(HashSet::new)));

		ResponseEntity<UserTagResponse> response3 = testRestTemplate.
				postForEntity("http://localhost:1917/api/tags", request3, UserTagResponse.class);
		
		assertThat(response3.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response3.getBody(), equalTo(expected3));
	}
	
	
/*
 * Latent add received after remove:
 */
	
	@Test
	public void test_latent_add_received_after_remove() throws Exception{
		
		UserTagRequest request = buildRequest("a", LocalDateTime.now(), ActionEnum.REMOVE);
		UserTagResponse expected = new UserTagResponse("1", new HashSet<String>());

		ResponseEntity<UserTagResponse> response = testRestTemplate.
				postForEntity("http://localhost:1917/api/tags", request, UserTagResponse.class);
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody(), equalTo(expected));
		
	
		// second push
		UserTagRequest request2 = buildRequest("a", LocalDateTime.now().minusHours(1), ActionEnum.ADD);
		UserTagResponse expected2 = new UserTagResponse("1", new HashSet<String>());

		ResponseEntity<UserTagResponse> response2 = testRestTemplate.
				postForEntity("http://localhost:1917/api/tags", request2, UserTagResponse.class);
		
		assertThat(response2.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response2.getBody(), equalTo(expected2));
	}


	private UserTagRequest buildRequest(String tag, LocalDateTime timestamp, ActionEnum type) {
		Set<String> tags = Stream.of(tag)
		  .collect(Collectors.toCollection(HashSet::new));
		switch (type) {
			case ADD:
				return new UserTagRequest("1", tags, null, timestamp);
			case REMOVE:
				return new UserTagRequest("1", null, tags, timestamp);
		}
		return null;
	}
}
