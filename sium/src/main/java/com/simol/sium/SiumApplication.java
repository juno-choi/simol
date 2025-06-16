package com.simol.sium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.simol.simolcommon.common.*", "com.simol.simolcommon.sium.*"})
@EntityScan(basePackages = {"com.simol.simolcommon.common.sium.*.entity"})
@EnableJpaRepositories(basePackages = {"com.simol.simolcommon.common.sium.*.repository"})
public class SiumApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiumApplication.class, args);
    }

}
