package behaviorTests.clients;

import behaviorTests.models.CaseCollectionEntityModel;
import behaviorTests.models.CaseEntityModel;
import behaviorTests.models.CaseStatusEntityModel;
import behaviorTests.models.CaseTypeEntityModel;
import behaviorTests.steps.StepContext;
import org.erpmicroservices.peopleandorganizations.api.rest.models.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class CaseClient {
    protected final RestTemplate template;
    private final Map<String, String> params;
    private final StepContext stepContext;
    //    @LocalServerPort
    protected int port = 8080;
    private final String baseUrl = "http://localhost:" + port;
    private final String url = baseUrl + "/cases";

    public CaseClient(RestTemplate template, StepContext stepContext) {
        this.template = template;
        params = new HashMap<>();
        int offset = 0;
        int limit = 10;
        params.put("page", String.valueOf(offset / limit));
        params.put("size", String.valueOf(limit));
        this.stepContext = stepContext;
    }

    public ResponseEntity<CaseEntityModel> save(Case caseToSave) {
        final HttpEntity<String> caseEntityModelHttpEntity = convertCaseToHttpStringEntity(caseToSave);
        return template.postForEntity(url, caseEntityModelHttpEntity, CaseEntityModel.class);
    }

    public void addCommunicationEventToCase(Case targetCase, CommunicationEvent communicationEvent) {
        stepContext.expectedCommunicationEvents.forEach(event -> {
            final HttpEntity<String> communicationEventEntityModelHttpEntity = convertCommunicationEventToHttpStringEntity(communicationEvent);
            template.put(url + "/" + targetCase.getId() + "/communicationEvents", communicationEventEntityModelHttpEntity);
        });
    }

    public ResponseEntity<CaseEntityModel> findCaseById(UUID id) {

        return template.getForEntity(url + "/" + id.toString(), CaseEntityModel.class, params);
    }

    public ResponseEntity<CaseEntityModel> update(Case expectedCase) {
        template.put(url + "/" + Objects.requireNonNull(expectedCase.getId()), convertCaseToHttpStringEntity(expectedCase));
        return findCaseById(expectedCase.getId());
    }

    public ResponseEntity<Void> delete(Case caseToDelete) {
        return template.exchange(url + "/" + caseToDelete.getId(), HttpMethod.DELETE, null, Void.class);

    }

    public @NotNull ResponseEntity<CaseCollectionEntityModel> getCaseCollectionEntityModelResponseEntity() {
        return template
                .getForEntity(url, CaseCollectionEntityModel.class, params);
    }

    public Optional<CaseStatusType> getCaseStatusTypeFromEntity(EntityModel<Case> aCaseEntity) {
        ResponseEntity<CaseStatusEntityModel> caseStatusCollectionModelResponseEntity = template
                .getForEntity(aCaseEntity
                                .getLink("caseStatus")
                                .stream()
                                .findFirst()
                                .orElseThrow()
                                .toUri(),
                        CaseStatusEntityModel.class);
        if (caseStatusCollectionModelResponseEntity.hasBody()) {
            final CaseStatusEntityModel caseStatusEntityModel = caseStatusCollectionModelResponseEntity.getBody();

            assert caseStatusEntityModel != null;
            if (caseStatusEntityModel.getContent() != null) {
                final CaseStatusType caseStatusType = caseStatusEntityModel.getContent();
                final UUID idFromEntity = getIdFromEntity(caseStatusEntityModel);
                return Optional.of(CaseStatusType.builder()
                        .id(idFromEntity)
                        .description(caseStatusType.getDescription())
                        .children(caseStatusType.getChildren())
                        .parent(caseStatusType.getParent())
                        .build());
            }
        }
        return Optional.empty();
    }

    public Optional<CaseType> getCaseTypeFromEntity(EntityModel<Case> aCaseEntity) {
        ResponseEntity<CaseTypeEntityModel> caseTypeEntityModelResponseEntity = template
                .getForEntity(aCaseEntity
                                .getLink("type")
                                .stream()
                                .findFirst()
                                .orElseThrow()
                                .getHref()
                        , CaseTypeEntityModel.class);
        if (caseTypeEntityModelResponseEntity.hasBody()) {
            final CaseTypeEntityModel body = caseTypeEntityModelResponseEntity.getBody();
            assert body != null;
            final CaseType caseType = body.getContent();
            if (caseType != null) {
                final UUID idFromEntity = getIdFromEntity(body);
                return Optional.of(CaseType.builder()
                        .id(idFromEntity)
                        .description(caseType.getDescription())
                        .children(caseType.getChildren())
                        .parent(caseType.getParent())
                        .build());
            }
        }
        return Optional.empty();
    }

    public @NotNull UUID getIdFromEntity(EntityModel<?> aCaseEntity) {
        final String self = aCaseEntity.getLinks("self").stream().findFirst().orElseThrow().getHref();
        final String selfId = self.substring(self.lastIndexOf('/') + 1);
        return UUID.fromString(selfId);
    }

    private static @NotNull HttpEntity<String> convertCaseToHttpStringEntity(Case caseToSave) {
        String stupidJson = """
                  {
                  "description": "%s",
                  "startedAt": "%s",
                  "caseStatus": "http://localhost:8080/caseStatusTypes/%s",
                  "type": "http://localhost:8080/caseTypes/%s"
                }
                """.formatted(caseToSave.getDescription(),
                caseToSave.getStartedAt(),
                caseToSave.getCaseStatus().getId(),
                caseToSave.getType().getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(stupidJson, headers);
    }

    private HttpEntity<String> convertCommunicationEventToHttpStringEntity(CommunicationEvent communicationEvent) {
        String stupidJson = """
                  http://localhost:8080/communicationEvents/%s
                """.formatted(communicationEvent.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/uri-list");
        return new HttpEntity<>(stupidJson, headers);
    }

    private HttpEntity<String> convertCaseRoleToHttpStringEntity(CaseRole caseRole) {
        String stupidJson = """
                {
                    "caseRoleType": "http://localhost:8080/caseRoles/%s",
                    "fromDate": "%s",
                    "party": "http://localhost:8080/parties/%s"
                }
                """.formatted(
                caseRole.getType().getId(),
                caseRole.getFromDate(),
                caseRole.getParty().getId()
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(stupidJson, headers);
    }

}
