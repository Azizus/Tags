package com.airship.tags.rest.domain;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TagRequest {

	@NotNull
	private Long userId;
	private List<String> add;
	private List<String> remove;
}
