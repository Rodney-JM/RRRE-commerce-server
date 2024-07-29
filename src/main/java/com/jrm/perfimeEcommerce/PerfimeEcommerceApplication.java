package com.jrm.perfimeEcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PerfimeEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerfimeEcommerceApplication.class, args);
	}

}
