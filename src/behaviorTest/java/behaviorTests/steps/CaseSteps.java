package behaviorTests.steps;

import behaviorTests.CucumberSpringBootContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.erpmicroservices.peopleandorganizations.api.rest.models.Case;
import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseStatusType;
import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseType;
import org.erpmicroservices.peopleandorganizations.api.rest.repositories.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.erpmicroservices.peopleandorganizations.builders.DateTimeTestDataBuilder.zonedDateTimeNow;

public class CaseSteps extends CucumberSpringBootContext {

    private final List<Case> expectedCases = new ArrayList<>();
    private final int offset = 0;
    private final int limit = 10;
    private final Map<String, Integer> params;
    private final String url = "http://localhost:" + port;
    private List<Case> actualCases = new ArrayList<>();
    private Case expectedCase = new Case();

    @Given("there are {int} cases with a type of {string} with a status of {string} in the database")
    public void there_are_cases_with_a_type_of_with_a_status_of_in_the_database(Integer numberOfCases, String caseTypeDescription, String caseStatusDescription) {
        final CaseType caseType = caseTypeRepo.findByDescription(caseTypeDescription);
        final CaseStatusType caseStatusType = caseStatusTypeRepo.findByDescription(caseStatusDescription);

        for (int i = 0; i < numberOfCases; i++) {
            expectedCases.add(caseRepo.save(Case.builder()
                    .caseStatus(caseStatusType)
                    .type(caseType)
                    .description("there_are_cases_with_a_type_of_with_a_status_of_in_the_database " + i)
                    .startedAt(zonedDateTimeNow())
                    .build()));
        }
    }

    @Given("a case description of {string}")
    public void a_case_description_of(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("I search for all cases")
    public void i_search_for_all_cases() {

        final ResponseEntity<CaseCollectionEntityModel> caseCollectionModelResponseEntity = template
                .getForEntity(url + "/cases", CaseCollectionEntityModel.class, params);
        actualCases = Optional.ofNullable(caseCollectionModelResponseEntity.getBody())
                .map(entity -> entity.getContent().stream()
                        .map(aCaseEntity -> {
                            final CaseType caseType = getCaseTypeFromEntity(aCaseEntity);
                            final CaseStatusType caseStatus = getCaseStatusTypeFromEntity(aCaseEntity);
                            final UUID selfId = getIdFromEntity(aCaseEntity);
                            final Case aCase = aCaseEntity.getContent();
                            assert aCase != null;
                            return Case.builder()
                                    .startedAt(aCase.getStartedAt())
                                    .caseStatus(caseStatus)
                                    .type(caseType)
                                    .description(aCase.getDescription())
                                    .id(selfId)
                                    .build();
                        }))
                .orElseThrow()
                .toList();
    }

    private @Nullable CaseStatusType getCaseStatusTypeFromEntity(EntityModel<Case> aCaseEntity) {
        ResponseEntity<CaseStatusEntityModel> caseStatusCollectionModelResponseEntity = template.getForEntity(aCaseEntity.getLink("caseStatus").stream().findFirst().orElseThrow().toUri(), CaseStatusEntityModel.class);
        return Objects.requireNonNull(caseStatusCollectionModelResponseEntity.getBody()).getContent();
    }

    private @Nullable CaseType getCaseTypeFromEntity(EntityModel<Case> aCaseEntity) {
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

    private static @NotNull UUID getIdFromEntity(EntityModel<Case> aCaseEntity) {
        final String self = aCaseEntity.getLinks("self").stream().findFirst().orElseThrow().getHref();
        final String selfId = self.substring(self.lastIndexOf('/') + 1);
        return UUID.fromString(selfId);
    }

    @When("I search for cases of type {string}")
    public void i_search_for_cases_of_type(String caseTypeDescription) {
        actualCases = caseRepo.findAllByType_Description(caseTypeDescription);
    }

    @When("I search for cases with a status of {string}")
    public void i_search_for_cases_with_a_status_of(String caseStatusDescription) {
        actualCases = caseRepo.findAllByCaseStatus_Description(caseStatusDescription);
    }

    @Then("I get {int} cases")
    public void i_get_cases(Integer numberOfCases) {
        Assert.assertEquals(numberOfCases.longValue(), actualCases.size());
    }

    @Then("{int} of them are cases of type {string}")
    public void of_them_are_cases_of_type(Integer numberOfCases, String caseType) {
        Assert.assertEquals(
                numberOfCases.longValue(),
                actualCases.stream()
                        .filter(c ->
                                c.getType().getDescription().equals(caseType))
                        .toList()
                        .size()
        );
    }

    @Then("{int} of them are cases in status {string}")
    public void of_them_are_cases_in_status(Integer numberOfCases, String status) {
        Assert.assertEquals(numberOfCases.longValue(),
                actualCases.stream()
                        .filter(c -> c.getCaseStatus().getDescription().equals(status))
                        .toList()
                        .size());
    }

    public CaseSteps(RestTemplate template, CaseStatusTypeRepo caseStatusTypeRepo, CaseTypeRepo caseTypeRepo, CaseRepo caseRepo, PartyTypeRepo partyTypeRepo, PartyRepo partyRepo, CaseRoleTypeRepo caseRoleTypeRepo, CaseRoleRepo caseRoleRepo, ContactMechanismTypeRepo contactMechanismTypeRepo, PartyRoleTypeRepo partyRoleTypeRepo, PartyRoleRepo partyRoleRepo, CommunicationEventStatusTypeRepo communicationEventStatusTypeRepo, CommunicationEventTypeRepo communicationEventTypeRepo, PartyRelationshipTypeRepo partyRelationshipTypeRepo, PartyRelationshipStatusTypeRepo partyRelationshipStatusTypeRepo, PriorityTypeRepo priorityTypeRepo, PartyRelationshipRepo partyRelationshipRepo, CommunicationEventRepo communicationEventRepo, FacilityRepo facilityRepo, FacilityTypeRepo facilityTypeRepo, FacilityRoleTypeRepo facilityRoleTypeRepo, FacilityRoleRepo facilityRoleRepo, FacilityContactMechanismRepo facilityContactMechanismRepo, ContactMechanismRepo contactMechanismRepo, GeographicBoundaryRepo geographicBoundaryRepo, GeographicBoundaryTypeRepo geographicBoundaryTypeRepo, ContactMechanismGeographicBoundaryRepo contactMechanismGeographicBoundaryRepo, PartyContactMechanismRepo partyContactMechanismRepo, PartyContactMechanismPurposeRepo partyContactMechanismPurposeRepo, PartyContactMechanismPurposeTypeRepo partyContactMechanismPurposeTypeRepo, CommunicationEventPurposeTypeRepo communicationEventPurposeTypeRepo, CommunicationEventRoleTypeRepo communicationEventRoleTypeRepo) {
        super(template, caseStatusTypeRepo, caseTypeRepo, caseRepo, partyTypeRepo, partyRepo, caseRoleTypeRepo, caseRoleRepo, contactMechanismTypeRepo, partyRoleTypeRepo, partyRoleRepo, communicationEventStatusTypeRepo, communicationEventTypeRepo, partyRelationshipTypeRepo, partyRelationshipStatusTypeRepo, priorityTypeRepo, partyRelationshipRepo, communicationEventRepo, facilityRepo, facilityTypeRepo, facilityRoleTypeRepo, facilityRoleRepo, facilityContactMechanismRepo, contactMechanismRepo, geographicBoundaryRepo, geographicBoundaryTypeRepo, contactMechanismGeographicBoundaryRepo, partyContactMechanismRepo, partyContactMechanismPurposeRepo, partyContactMechanismPurposeTypeRepo, communicationEventPurposeTypeRepo, communicationEventRoleTypeRepo);
        params = new HashMap<>();
        params.put("page", offset / limit);
        params.put("size", limit);
    }
}
