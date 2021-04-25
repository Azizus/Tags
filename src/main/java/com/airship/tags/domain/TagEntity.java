package com.airship.tags.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TagEntity {

	private Long userId;

	private Set<String> tags;
	
	private LocalDateTime date;
}
