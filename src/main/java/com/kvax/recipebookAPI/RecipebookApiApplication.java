package com.kvax.recipebookAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RecipebookApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipebookApiApplication.class, args);
	}

}
