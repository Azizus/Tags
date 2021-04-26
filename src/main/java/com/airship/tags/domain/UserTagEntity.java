package com.airship.tags.domain;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserTagEntity {

	private Long userId;

	private Set<TagEntity> tags;
	
	private LocalDateTime date;
}
