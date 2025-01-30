package behaviorTests.clients;

import behaviorTests.models.*;
import behaviorTests.steps.StepContext;
import org.erpmicroservices.peopleandorganizations.api.rest.models.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Lazy
@Component
public class CaseClient extends BaseHATEOASClient {

    public CaseClient(RestTemplate RestTemplate, StepContext stepContext) {
        super(RestTemplate, stepContext);
    }

    private URI casesUrl() {

        return url().pathSegment("cases").build().toUri();
    }

    private URI caseRolesUrl() {
        return url().pathSegment("caseRoles").build().toUri();
    }

    public ResponseEntity<CaseEntityModel> save(Case caseToSave) {
        final HttpEntity<String> caseEntityModelHttpEntity = convertCaseToHttpStringEntity(caseToSave);
        return template.postForEntity(casesUrl(), caseEntityModelHttpEntity, CaseEntityModel.class);
    }

    public void addCommunicationEventToCase(Case targetCase, CommunicationEvent communicationEvent) {
        stepContext.expectedCommunicationEvents.forEach(event -> {
            final HttpEntity<String> communicationEventEntityModelHttpEntity = convertCommunicationEventToHttpStringEntity(communicationEvent);
            template.put(casesUrl() + "/" + targetCase.getId() + "/communicationEvents", communicationEventEntityModelHttpEntity);
        });
    }

    public ResponseEntity<CaseEntityModel> findCaseById(UUID id) {

        return template.getForEntity(casesUrl() + "/" + id.toString(), CaseEntityModel.class, params);
    }

    public ResponseEntity<CaseEntityModel> update(Case expectedCase) {
        template.put(casesUrl() + "/" + Objects.requireNonNull(expectedCase.getId()), convertCaseToHttpStringEntity(expectedCase));
        return findCaseById(expectedCase.getId());
    }

    public ResponseEntity<CaseRoleEntityModel> addCaseRoleToCase(CaseRole caseRole) {
        final HttpEntity<String> caseRoleEntityModelHttpEntity = convertCaseRoleToHttpStringEntity(caseRole);
        return template.postForEntity(caseRolesUrl(), caseRoleEntityModelHttpEntity, CaseRoleEntityModel.class);
    }

    public ResponseEntity<CaseRoleEntityModel> updateCaseRole(CaseRole caseRole) {
        final HttpEntity<String> caseRoleEntityModelHttpEntity = convertCaseRoleToHttpStringEntity(caseRole);
        return template.exchange(caseRolesUrl() + "/" + caseRole.getId()
                , HttpMethod.PUT
                , caseRoleEntityModelHttpEntity
                , CaseRoleEntityModel.class);
    }

    public ResponseEntity<Void> delete(Case caseToDelete) {
        return template.exchange(casesUrl() + "/" + caseToDelete.getId(), HttpMethod.DELETE, null, Void.class);

    }

    public @NotNull ResponseEntity<CaseCollectionEntityModel> getCaseCollectionEntityModelResponseEntity() {
        return template
                .getForEntity(casesUrl().toString(), CaseCollectionEntityModel.class, params);
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
                 "fromDate": "%s",
                 "thruDate": "%s",
                 "type": "http://localhost:8080/caseRoleTypes/%s",
                 "party": "http://localhost:8080/parties/%s",
                 "kase": "http://localhost:8080/cases/%s"
                }
                """.formatted(
                caseRole.getFromDate(),
                caseRole.getThruDate() == null ? "" : caseRole.getThruDate(),
                caseRole.getType().getId(),
                caseRole.getParty().getId(),
                caseRole.getKase().getId());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/hal+json");
        return new HttpEntity<>(stupidJson, headers);
    }

    public ResponseEntity<CaseRoleCollectionEntityModel> getCaseRolesFromCase(Case expectedCase) {
        return template.getForEntity(casesUrl() + "/" + expectedCase.getId() + "/roles", CaseRoleCollectionEntityModel.class, params);
    }

    public void deleteCaseRole(CaseRole caseRole) {
        template.delete(caseRolesUrl() + "/" + caseRole.getId());
    }
}
