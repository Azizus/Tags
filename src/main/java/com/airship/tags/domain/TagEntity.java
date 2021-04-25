package com.airship.tags.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class TagEntity {

	@Id
	private Long userId;

	private List<String> add;

	private List<String> remove;
	
	private Date date;
}
