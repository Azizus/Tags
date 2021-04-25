package com.airship.tags;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.airship.tags.rest.controller.TagController;

@SpringBootTest
public class SmokeTest {

	@Autowired
	private TagController tagController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(tagController).isNotNull();
	}
}
