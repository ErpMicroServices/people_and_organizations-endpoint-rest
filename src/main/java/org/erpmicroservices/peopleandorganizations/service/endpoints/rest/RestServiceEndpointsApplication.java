package org.erpmicroservices.peopleandorganizations.service.endpoints.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan("org.erp_microservices")
@SpringBootApplication
public class RestServiceEndpointsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceEndpointsApplication.class, args);
	}
}
