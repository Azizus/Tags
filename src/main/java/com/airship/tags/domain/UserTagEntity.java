package com.airship.tags.domain;

import java.util.Set;

import com.airship.tags.domain.nonpersistant.Tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserTagEntity {

	private String userId;

	private Set<Tag> tags;
}
