package com.airship.tags.serviceImpl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.airship.tags.domain.UserTagEntity;
import com.airship.tags.repository.UserTagRepository;
import com.airship.tags.rest.domain.UserTagRequest;
import com.airship.tags.rest.domain.UserTagResponse;
import com.airship.tags.rest.mapper.UserTagRestMapper;
import com.airship.tags.utils.ActionEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTagServiceImplTest {
	
	@Autowired
	private UserTagServiceImpl userTagServiceImpl;
		
	@MockBean
	private UserTagRepository userTagRepository;
	
	@MockBean
	private UserTagRestMapper userTagRestMapper;
	
	@Test
	public void should_return_user_tag_entity_with_latest_action() {
		
		Set<String> add = new HashSet<>(Arrays.asList("a", "b", "c"));
		Set<String> remove = new HashSet<>(Arrays.asList("b"));
		UserTagRequest request = new UserTagRequest("1", add, remove, LocalDateTime.now());
				
		UserTagEntity userTag1 = new UserTagEntity("1", "a", ActionEnum.ADD, LocalDateTime.now());
		UserTagEntity userTag2 = new UserTagEntity("1", "b", ActionEnum.ADD, LocalDateTime.now());
		UserTagEntity userTag3 = new UserTagEntity("1", "c", ActionEnum.ADD, LocalDateTime.now());
		UserTagEntity userTag4 = new UserTagEntity("1", "b", ActionEnum.REMOVE, LocalDateTime.now());
				
		Mockito.when(userTagRepository.save(userTag1)).thenReturn(userTag1);
		Mockito.when(userTagRepository.save(userTag2)).thenReturn(userTag2);
		Mockito.when(userTagRepository.save(userTag3)).thenReturn(userTag3);
		Mockito.when(userTagRepository.save(userTag4)).thenReturn(userTag4);

		
		Mockito.when(userTagRestMapper.UserTagRequestToUserTagEntity(
				"1", LocalDateTime.now(), "a", ActionEnum.ADD)).thenReturn(userTag1);
		
		Mockito.when(userTagRestMapper.UserTagRequestToUserTagEntity(
				"1", LocalDateTime.now(), "b", ActionEnum.ADD)).thenReturn(userTag2);
		
		Mockito.when(userTagRestMapper.UserTagRequestToUserTagEntity(
				"1", LocalDateTime.now(), "c", ActionEnum.ADD)).thenReturn(userTag3);
		
		Mockito.when(userTagRestMapper.UserTagRequestToUserTagEntity(
				"1", LocalDateTime.now(), "b", ActionEnum.REMOVE)).thenReturn(userTag4);


		Set<String> tags = new HashSet<>(Arrays.asList("a", "c"));
		UserTagResponse response = new UserTagResponse("1", tags);


		// need to mock the private method
		
		UserTagResponse expected = new UserTagResponse("1", tags);
		
		UserTagResponse actual = userTagServiceImpl.pushTag(request);

		assertThat(actual, equalTo(expected));
		
	}

}
