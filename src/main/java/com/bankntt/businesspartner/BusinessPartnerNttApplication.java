package com.bankntt.businesspartner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BusinessPartnerNttApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessPartnerNttApplication.class, args);
	}

}
