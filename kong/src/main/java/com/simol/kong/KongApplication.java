package com.simol.kong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.simol.simolcommon.common.*", "com.simol.simolcommon.kong.*", "com.simol.kong.*"})
@EntityScan(basePackages = {"com.simol.simolcommon.common.*", "com.simol.simolcommon.kong.*"})
@EnableJpaRepositories(basePackages = {"com.simol.simolcommon.common.*", "com.simol.simolcommon.kong.*"})
public class KongApplication {

	public static void main(String[] args) {
		SpringApplication.run(KongApplication.class, args);
	}

}
