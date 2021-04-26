package com.airship.tags.rest.domain;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserTagResponse {

	private Long userId;
	private Set<String> tags;
}