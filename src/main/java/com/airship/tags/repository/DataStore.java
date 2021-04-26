package com.airship.tags.repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.airship.tags.domain.UserTagEntity;

public class DataStore {

	public static Set<UserTagEntity> tags = Collections.synchronizedSet(new HashSet<>());
}
