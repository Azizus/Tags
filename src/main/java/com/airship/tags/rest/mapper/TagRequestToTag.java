package com.airship.tags.rest.mapper;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TagRequestToTag {
	
	private Long userId;
	private List<String> add;
	private List<String> remove;
	private Date date;

}
