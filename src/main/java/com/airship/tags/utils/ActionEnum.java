package com.airship.tags.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ActionEnum {
	ADD(0), REMOVE(1);
	
	private int weight;

}
