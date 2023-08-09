package com.thonwelling.fooddelivery;

import com.thonwelling.fooddelivery.repositories.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class StartUp {
	public static void main(String[] args) {
		SpringApplication.run(StartUp.class, args);
	}
}