package behaviorTests;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Lazy
@TestConfiguration(proxyBeanMethods = false)
public class RestClientConfiguration {

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder,@Value("${local.server.port:8080}") int randomPort) {
        return webClientBuilder
                .baseUrl("http://localhost:" + randomPort)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, String.valueOf(MediaTypes.HAL_JSON))
                .build();
    }
}
