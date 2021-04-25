package com.airship.tags.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TagEntity {

	private Long userId;

	private List<String> add;

	private List<String> remove;
	
	private Date date;
}
