package com.simol.simoluser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.simol.ouncommon.*", "com.simol.simoluser.*"})
@EntityScan(basePackages = {"com.simol.ouncommon.*.entity"})
@EnableJpaRepositories(basePackages = {"com.simol.ouncommon.*.repository"})
public class SimolUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimolUserApplication.class, args);
	}

}
