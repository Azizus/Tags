package com.airship.tags.serviceImpl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airship.tags.domain.UserTagEntity;
import com.airship.tags.repository.UserTagRepository;
import com.airship.tags.rest.domain.UserTagRequest;
import com.airship.tags.rest.domain.UserTagResponse;
import com.airship.tags.rest.mapper.UserTagRestMapper;
import com.airship.tags.service.UserTagService;
import com.airship.tags.utils.ActionEnum;
import com.airship.tags.utils.CleanDuplication;

@Service
public class UserTagServiceImpl implements UserTagService {

	@Autowired
	private UserTagRepository userTagRepository;

	@Autowired
	private UserTagRestMapper userTagRestMapper;

	@Autowired
	private CleanDuplication cleanDuplication;

	@Override
	public UserTagResponse pushTag(UserTagRequest userTagRequest) {

		userTagRequest = this.cleanAddAndRemoveTags(userTagRequest);

		for (String tagToAdd : userTagRequest.getAdd()) {
			UserTagEntity userTagEntity = new UserTagEntity();
			// pass only userid and timestamp
			userTagEntity = userTagRestMapper.UserTagRequestToUserTagEntity(userTagRequest, tagToAdd, ActionEnum.ADD);
			userTagRepository.save(userTagEntity);
		}

		for (String tagToRemove : userTagRequest.getRemove()) {
			UserTagEntity userTagEntity = new UserTagEntity();
			userTagEntity = userTagRestMapper.UserTagRequestToUserTagEntity(userTagRequest, tagToRemove,
					ActionEnum.REMOVE);
			userTagRepository.save(userTagEntity);
		}

		return this.findMostRecentUserTags(userTagRequest.getUser());
	}

	private UserTagRequest cleanAddAndRemoveTags(UserTagRequest userTagRequest) {

		Map<String, Set<String>> cleaned = new HashMap<>();

		cleaned = cleanDuplication.cleanSetsDuplication(userTagRequest.getAdd(), userTagRequest.getRemove());

		userTagRequest.setAdd(cleaned.get("add"));
		userTagRequest.setRemove(cleaned.get("remove"));

		return userTagRequest;
	}

	@Override
	public Set<UserTagEntity> findAllUserTagEntitiesByUserId(String userId) {
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
