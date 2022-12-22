package com.banquemisr.irrigationApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@EnableRetry
@SpringBootApplication
@EnableJpaRepositories
public class IrrigationAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(IrrigationAppApplication.class, args);
	}

}
