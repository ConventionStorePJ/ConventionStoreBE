package com.convention_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ConventionStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConventionStoreApplication.class, args);
	}

}
