package behaviorTests.clients;

import behaviorTests.steps.StepContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BaseHATEOASClient {
    protected final RestTemplate template;
    protected final Map<String, Object> params;
    protected final StepContext stepContext;
    protected final String paginationStringTemplate = "page={page}&size={size}";

    public BaseHATEOASClient(RestTemplate RestTemplate, StepContext stepContext) {
        this.template = RestTemplate;
        params = new HashMap<>();
        this.stepContext = stepContext;
        int offset = 0;
        int limit = 10;
        params.put("page", String.valueOf(offset / limit));
        params.put("size", String.valueOf(limit));
    }

    protected URI url() {
        return URI.create("http://localhost:8080/");
    }

    public @NotNull UUID getIdFromEntity(EntityModel<?> aCaseEntity) {
        final String self = aCaseEntity.getLinks("self").stream().findFirst().orElseThrow().getHref();
        final String selfId = self.substring(self.lastIndexOf('/') + 1);
        return UUID.fromString(selfId);
    }
}
