package org.erpmicroservices.peopleandorganizations.api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PeopleAndOrganizationsApiRestApplication {

//    @Autowired
//    private ObjectMapper objectMapper;

    public static void main(String... args) {
        SpringApplication.run(PeopleAndOrganizationsApiRestApplication.class, args);
    }

//    @PostConstruct
//    public void setUp() {
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
//    }
}
