package com.simol.ounuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.simol.ouncommon"})
@EnableJpaRepositories(basePackages = {"com.simol.ouncommon"})
public class OunUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(OunUserApplication.class, args);
	}

}
