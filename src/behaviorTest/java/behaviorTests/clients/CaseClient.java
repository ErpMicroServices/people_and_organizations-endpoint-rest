package behaviorTests.clients;

import behaviorTests.models.CaseCollectionEntityModel;
import behaviorTests.models.CaseStatusEntityModel;
import behaviorTests.models.CaseTypeEntityModel;
import org.erpmicroservices.peopleandorganizations.api.rest.models.Case;
import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseStatusType;
import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
public class CaseClient {
    protected final RestTemplate template;
    private final int offset = 0;
    private final int limit = 10;
    private final Map<String, Integer> params;
    //    @LocalServerPort
    protected int port = 8080;
    private final String url = "http://localhost:" + port;

    public CaseClient(RestTemplate template) {
        this.template = template;
        params = new HashMap<>();
        params.put("page", offset / limit);
        params.put("size", limit);
    }

    public @NotNull ResponseEntity<CaseCollectionEntityModel> getCaseCollectionEntityModelResponseEntity() {
        return template
                .getForEntity(url + "/cases", CaseCollectionEntityModel.class, params);
    }

    public @Nullable CaseStatusType getCaseStatusTypeFromEntity(EntityModel<Case> aCaseEntity) {
        ResponseEntity<CaseStatusEntityModel> caseStatusCollectionModelResponseEntity = template.getForEntity(aCaseEntity.getLink("caseStatus").stream().findFirst().orElseThrow().toUri(), CaseStatusEntityModel.class);
        return Objects.requireNonNull(caseStatusCollectionModelResponseEntity.getBody()).getContent();
    }

    public @Nullable CaseType getCaseTypeFromEntity(EntityModel<Case> aCaseEntity) {
        ResponseEntity<CaseTypeEntityModel> caseTypeCollectionModelResponseEntity = template
                .getForEntity(aCaseEntity
                                .getLink("type")
                                .stream()
                                .findFirst()
                                .orElseThrow()
                                .getHref()
                        , CaseTypeEntityModel.class);
        return caseTypeCollectionModelResponseEntity.getBody()
                .getContent();
    }

    public @NotNull UUID getIdFromEntity(EntityModel<Case> aCaseEntity) {
        final String self = aCaseEntity.getLinks("self").stream().findFirst().orElseThrow().getHref();
        final String selfId = self.substring(self.lastIndexOf('/') + 1);
        return UUID.fromString(selfId);
    }

}
