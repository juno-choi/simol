package com.simol.ouncommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.simol.ouncommon"})
@EnableJpaRepositories(basePackages = {"com.simol.ouncommon"})
public class OunCommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(OunCommonApplication.class, args);
	}

}
