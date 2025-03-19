package com.simol.ounapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.simol.simolcommon.*", "com.simol.ounapi.*"})
@EntityScan(basePackages = {"com.simol.simolcommon.*.entity"})
@EnableJpaRepositories(basePackages = {"com.simol.simolcommon.*.repository"})
public class OunApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OunApiApplication.class, args);
	}

}
