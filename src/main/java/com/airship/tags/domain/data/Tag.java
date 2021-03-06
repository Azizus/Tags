package com.airship.tags.domain.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tag {
	
	private String tag;
	private List<Action> actions;

}
