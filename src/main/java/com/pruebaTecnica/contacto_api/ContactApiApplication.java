package com.pruebaTecnica.contacto_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.pruebaTecnica")
@EnableJpaRepositories(basePackages = "com.pruebaTecnica.repository")
@EntityScan(basePackages = "org.openapitools.model")
public class ContactApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactApiApplication.class, args);
	}

}
