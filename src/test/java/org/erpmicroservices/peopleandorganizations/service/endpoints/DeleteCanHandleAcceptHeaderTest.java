package org.erpmicroservices.peopleandorganizations.service.endpoints;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.erpmicroservices.peopleandorganizations.api.rest.repositories.CaseRoleTypeRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WireMockTest(httpPort = 9080)
public class DeleteCanHandleAcceptHeaderTest extends AbstractGwtTestTemplate {

    private static RestTemplate mockRestTemplate;

    private static CaseRoleTypeRepo serviceUnderTest;

    @BeforeEach
    @Override
    public void given() {
        mockRestTemplate = mock(RestTemplate.class);
    }

    @Test
    @Override
    public void when() {
    }

    @AfterEach
    @Override
    public void then() {

    }
}
