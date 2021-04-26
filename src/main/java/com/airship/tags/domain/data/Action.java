package com.airship.tags.domain.data;

import java.time.LocalDateTime;

import com.airship.tags.utils.ActionEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Action {

	private LocalDateTime timestamp;
	private ActionEnum action ;
}
