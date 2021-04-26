package com.airship.tags.domain;

import java.util.List;

import com.airship.tags.domain.nonpersistant.Action;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TagEntity {
	
	private String tag;
	private List<Action> actions;
}
