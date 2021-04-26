package com.airship.tags.serviceImpl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.airship.tags.domain.UserTagEntity;
import com.airship.tags.repository.UserTagRepository;
import com.airship.tags.rest.domain.UserTagRequest;
import com.airship.tags.rest.domain.UserTagResponse;
import com.airship.tags.rest.mapper.UserTagRestMapper;
import com.airship.tags.service.UserTagService;
import com.airship.tags.utils.ActionEnum;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserTagServiceImpl implements UserTagService {


	private final UserTagRepository userTagRepository;

	
	private final UserTagRestMapper userTagRestMapper;

	@Override
	public UserTagResponse pushTag(UserTagRequest userTagRequest) {
		
		if (userTagRequest.getAdd() != null && userTagRequest.getRemove() != null) {
			userTagRequest.getAdd().removeIf(tag -> userTagRequest.getRemove().contains(tag));
		}

		mapTagFromRequestToEntity(userTagRequest.getUser(), userTagRequest.getTimestamp(), userTagRequest.getAdd(), ActionEnum.ADD)
			.forEach(userTagRepository::save);

		mapTagFromRequestToEntity(userTagRequest.getUser(), userTagRequest.getTimestamp(), userTagRequest.getRemove(), ActionEnum.REMOVE)
			.forEach(userTagRepository::save);
		
		return this.findMostRecentUserTags(userTagRequest.getUser());
	}
	
	private Stream<UserTagEntity> mapTagFromRequestToEntity(String user, LocalDateTime timestamp, Set<String> tags, ActionEnum action) {
		if (tags == null)
			return Stream.empty();
		return tags.stream()
		.map(tag -> userTagRestMapper.UserTagRequestToUserTagEntity(user, timestamp, tag, action));
	}


	private Set<UserTagEntity> findAllUserTagEntitiesByUserId(String userId) {
		return userTagRepository.getByUserId(userId);
	}

	private UserTagResponse findMostRecentUserTags(String userId) {

		Set<UserTagEntity> userTagEntities = this.findAllUserTagEntitiesByUserId(userId);

		Set<String> tags = userTagEntities.stream().collect(Collectors.groupingBy(UserTagEntity::getTag)).values()
				.stream().map(e -> keepLatestAction(e)).filter(e -> e != null && ActionEnum.ADD == e.getAction())
				.map(e -> e.getTag()).collect(Collectors.toSet());

		return new UserTagResponse(userId, tags);
	}

	private UserTagEntity keepLatestAction(List<UserTagEntity> actions) {

		return actions.stream()
				.max(Comparator.comparing(UserTagEntity::getTimestamp).thenComparingInt(e -> e.getAction().getWeight()))
				.orElse(null);

	}
}
