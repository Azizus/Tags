package com.airship.tags;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TagsApplication {

	public static void main(String[] args) {
		Thread hookShutDown = new Thread(
				() -> System.out.println("clean shutdown of the application"));
		Runtime.getRuntime().addShutdownHook(hookShutDown);
		SpringApplication.run(TagsApplication.class, args);
	}

}
