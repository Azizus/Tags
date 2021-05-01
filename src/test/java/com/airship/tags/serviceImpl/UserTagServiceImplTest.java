package com.airship.tags.serviceImpl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.airship.tags.repository.UserTagRepository;
import com.airship.tags.rest.domain.UserTagRequest;
import com.airship.tags.rest.domain.UserTagResponse;
import com.airship.tags.rest.mapper.UserTagRestMapper;
import com.airship.tags.utils.ActionEnum;

@SpringBootTest
public class UserTagServiceImplTest {

	@Autowired
	private UserTagServiceImpl userTagServiceImpl;

	@Autowired
	private UserTagRestMapper userTagRestMapper;

	@Autowired
	private UserTagRepository userTagRepository;

	@Test
	public void should_return_reponse_when_push_user_tag() {

		UserTagRequest request = buildRequest("1", "a", LocalDateTime.now(), ActionEnum.ADD);

		UserTagResponse actual = userTagServiceImpl.pushTag(request);
		UserTagResponse expected = new UserTagResponse("1",
				Stream.of("a").collect(Collectors.toCollection(HashSet::new)));

		assertThat(actual, equalTo(expected));

	}

	private UserTagRequest buildRequest(String user, String tag, LocalDateTime timestamp, ActionEnum type) {
		Set<String> tags = Stream.of(tag).collect(Collectors.toCollection(HashSet::new));
		switch (type) {
		case ADD:
			return new UserTagRequest(user, tags, null, timestamp);
		case REMOVE:
			return new UserTagRequest(user, null, tags, timestamp);
		}
		return null;
	}
}
