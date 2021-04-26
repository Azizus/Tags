package com.airship.tags.domain;

import java.time.LocalDateTime;

import com.airship.tags.utils.ActionEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserTagEntity {

	private String userId;

	private String tag;
	
	private ActionEnum action;
	
	private LocalDateTime timestamp;
}
