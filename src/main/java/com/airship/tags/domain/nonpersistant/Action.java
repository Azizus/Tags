package com.airship.tags.domain.nonpersistant;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Action {

	private LocalDateTime timestamp;
	private String action ;
}
