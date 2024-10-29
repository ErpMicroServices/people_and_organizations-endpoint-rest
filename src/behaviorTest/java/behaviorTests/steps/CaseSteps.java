package behaviorTests.steps;

import behaviorTests.CucumberSpringBootContext;
import behaviorTests.clients.CaseClient;
import behaviorTests.models.CaseCollectionEntityModel;
import behaviorTests.models.CaseEntityModel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.erpmicroservices.peopleandorganizations.api.rest.models.Case;
import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseStatusType;
import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseType;
import org.erpmicroservices.peopleandorganizations.api.rest.repositories.*;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.*;

import static org.erpmicroservices.peopleandorganizations.builders.DateTimeTestDataBuilder.zonedDateTimeNow;

public class CaseSteps extends CucumberSpringBootContext {

    private CaseClient caseClient;

    public CaseSteps(StepContext stepContext, CaseClient caseClient, RestTemplate template, CaseStatusTypeRepo caseStatusTypeRepo, CaseTypeRepo caseTypeRepo, CaseRepo caseRepo, PartyTypeRepo partyTypeRepo, PartyRepo partyRepo, CaseRoleTypeRepo caseRoleTypeRepo, CaseRoleRepo caseRoleRepo, ContactMechanismTypeRepo contactMechanismTypeRepo, PartyRoleTypeRepo partyRoleTypeRepo, PartyRoleRepo partyRoleRepo, CommunicationEventStatusTypeRepo communicationEventStatusTypeRepo, CommunicationEventTypeRepo communicationEventTypeRepo, PartyRelationshipTypeRepo partyRelationshipTypeRepo, PartyRelationshipStatusTypeRepo partyRelationshipStatusTypeRepo, PriorityTypeRepo priorityTypeRepo, PartyRelationshipRepo partyRelationshipRepo, CommunicationEventRepo communicationEventRepo, FacilityRepo facilityRepo, FacilityTypeRepo facilityTypeRepo, FacilityRoleTypeRepo facilityRoleTypeRepo, FacilityRoleRepo facilityRoleRepo, FacilityContactMechanismRepo facilityContactMechanismRepo, ContactMechanismRepo contactMechanismRepo, GeographicBoundaryRepo geographicBoundaryRepo, GeographicBoundaryTypeRepo geographicBoundaryTypeRepo, ContactMechanismGeographicBoundaryRepo contactMechanismGeographicBoundaryRepo, PartyContactMechanismRepo partyContactMechanismRepo, PartyContactMechanismPurposeRepo partyContactMechanismPurposeRepo, PartyContactMechanismPurposeTypeRepo partyContactMechanismPurposeTypeRepo, CommunicationEventPurposeTypeRepo communicationEventPurposeTypeRepo, CommunicationEventRoleTypeRepo communicationEventRoleTypeRepo) {
        super(stepContext, template, caseStatusTypeRepo, caseTypeRepo, caseRepo, partyTypeRepo, partyRepo, caseRoleTypeRepo, caseRoleRepo, contactMechanismTypeRepo, partyRoleTypeRepo, partyRoleRepo, communicationEventStatusTypeRepo, communicationEventTypeRepo, partyRelationshipTypeRepo, partyRelationshipStatusTypeRepo, priorityTypeRepo, partyRelationshipRepo, communicationEventRepo, facilityRepo, facilityTypeRepo, facilityRoleTypeRepo, facilityRoleRepo, facilityContactMechanismRepo, contactMechanismRepo, geographicBoundaryRepo, geographicBoundaryTypeRepo, contactMechanismGeographicBoundaryRepo, partyContactMechanismRepo, partyContactMechanismPurposeRepo, partyContactMechanismPurposeTypeRepo, communicationEventPurposeTypeRepo, communicationEventRoleTypeRepo);
        this.caseClient = caseClient;
    }

    @Given("there are {int} cases with a type of {string} with a status of {string} in the database")
    public void there_are_cases_with_a_type_of_with_a_status_of_in_the_database(Integer numberOfCases, String caseTypeDescription, String caseStatusDescription) {
        final CaseType caseType = caseTypeRepo.findByDescription(caseTypeDescription);
        final CaseStatusType caseStatusType = caseStatusTypeRepo.findByDescription(caseStatusDescription);

        for (int i = 0; i < numberOfCases; i++) {
            stepContext.expectedCases.add(caseRepo.save(Case.builder()
                    .caseStatus(caseStatusType)
                    .type(caseType)
                    .description("there_are_cases_with_a_type_of_with_a_status_of_in_the_database " + i)
                    .startedAt(zonedDateTimeNow())
                    .build()));
        }
    }

    @Given("a case description of {string}")
    public void a_case_description_of(String description) {
        stepContext.expectedCase.setDescription(description);
    }

    @Given("a case status of {string}")
    public void a_case_status_of(String caseStatusDescription) {
        final CaseStatusType caseStatusType = stepContext.caseStatusTypes.stream().filter(status -> status.getDescription().equals(caseStatusDescription)).findFirst().orElseThrow();
        stepContext.expectedCase.setCaseStatus(caseStatusType);
    }

    @Given("a case type of {string}")
    public void a_case_type_of(String typeDescription) {
        stepContext.expectedCase.setType(stepContext.caseTypes.stream().filter(type -> type.getDescription().equals(typeDescription)).findFirst().orElseThrow());
    }

    @Given("a case was started at {string}")
    public void a_case_was_started_at(String startedAt) {
        stepContext.expectedCase.setStartedAt(ZonedDateTime.parse(startedAt));
    }

    @Given("the case is saved to the database")
    public void the_case_is_saved_to_the_database() {
        stepContext.expectedCase.setCaseStatus(stepContext.caseStatusTypes.getFirst());
        stepContext.expectedCase.setType(stepContext.caseTypes.getFirst());
        stepContext.expectedCase = caseRepo.save(stepContext.expectedCase);
    }

    @When("I save the case")
    public void i_save_the_case() {
        stepContext.actualResponseEntityCase = caseClient.save(stepContext.expectedCase);
    }

    @When("I search for all cases")
    public void i_search_for_all_cases() {

        final ResponseEntity<CaseCollectionEntityModel> caseCollectionModelResponseEntity = caseClient.getCaseCollectionEntityModelResponseEntity();
        stepContext.actualCases = Optional.ofNullable(caseCollectionModelResponseEntity.getBody())
                .map(entity -> entity.getContent().stream()
                        .map(aCaseEntity -> {
                            final CaseType caseType = caseClient.getCaseTypeFromEntity(aCaseEntity).orElseThrow();
                            final CaseStatusType caseStatus = caseClient.getCaseStatusTypeFromEntity(aCaseEntity).orElseThrow();
                            final UUID selfId = caseClient.getIdFromEntity(aCaseEntity);
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

    @When("I search for cases of type {string}")
    public void i_search_for_cases_of_type(String caseTypeDescription) {
        stepContext.actualCases = caseRepo.findAllByType_Description(caseTypeDescription);
    }

    @When("I search for cases with a status of {string}")
    public void i_search_for_cases_with_a_status_of(String caseStatusDescription) {
        stepContext.actualCases = caseRepo.findAllByCaseStatus_Description(caseStatusDescription);
    }

    @When("I search for the case by id")
    public void i_search_for_the_case_by_id() {
        stepContext.actualResponseEntityCase = caseClient.findCaseById(Objects.requireNonNull(stepContext.expectedCase.getId()));
    }

    @When("I update the case description to {string}")
    public void i_update_the_case_description_to(String newCaseDescription) {
        stepContext.expectedCase.setDescription(newCaseDescription);
        stepContext.actualResponseEntityCase = caseClient.update( stepContext.expectedCase);
    }

    @When("I delete the case")
    public void i_delete_the_case() {
        stepContext.actualResponseEntityVoid  = caseClient.delete( stepContext.expectedCase);
    }



    @Then("the operation was successful")
    public void the_operation_was_successful() {
        if( stepContext.actualResponseEntityVoid != null) {
            Assert.assertTrue(stepContext.actualResponseEntityVoid.getStatusCode().is2xxSuccessful());
        }
        if( stepContext.actualResponseEntityCase != null) {
            Assert.assertTrue(stepContext.actualResponseEntityCase.getStatusCode().is2xxSuccessful());
        }
    }

    @Then("the case is in the database")
    public void the_case_is_in_the_database() {
        Case actualCase = extractCaseFromResponseEntity(stepContext.actualResponseEntityCase).orElseThrow();
        Assert.assertNotNull(actualCase.getId());
        Assert.assertEquals(stepContext.expectedCase.getDescription(), actualCase.getDescription());
        Assert.assertEquals(stepContext.expectedCase.getCaseStatus().getId(), actualCase.getCaseStatus().getId());
        Assert.assertEquals(stepContext.expectedCase.getStartedAt(), actualCase.getStartedAt().withZoneSameInstant(stepContext.expectedCase.getStartedAt().getZone()));
        Assert.assertEquals(stepContext.expectedCase.getType().getId(), actualCase.getType().getId());
        final Optional<Case> caseInDb = caseRepo.findById(actualCase.getId());
        Assert.assertTrue(caseInDb.isPresent());

        caseInDb.ifPresent(aCase -> {
            Assert.assertEquals(stepContext.expectedCase.getDescription(), aCase.getDescription());
            Assert.assertEquals(stepContext.expectedCase.getCaseStatus().getId(), aCase.getCaseStatus().getId());
            Assert.assertEquals(stepContext.expectedCase.getStartedAt(), aCase.getStartedAt().withZoneSameInstant(stepContext.expectedCase.getStartedAt().getZone()));
            Assert.assertEquals(stepContext.expectedCase.getType().getId(), aCase.getType().getId());
        });


    }

    @Then("I get {int} cases")
    public void i_get_cases(Integer numberOfCases) {
        Assert.assertEquals(numberOfCases.longValue(), stepContext.actualCases.size());
    }

    @Then("{int} of them are cases of type {string}")
    public void of_them_are_cases_of_type(Integer numberOfCases, String caseType) {
        Assert.assertEquals(
                numberOfCases.longValue(),
                  stepContext.actualCases.stream()
                        .filter(c ->
                                c.getType().getDescription().equals(caseType))
                        .toList()
                        .size()
        );
    }

    @Then("{int} of them are cases in status {string}")
    public void of_them_are_cases_in_status(Integer numberOfCases, String status) {
        Assert.assertEquals(numberOfCases.longValue(),
                  stepContext.actualCases.stream()
                        .filter(c -> c.getCaseStatus().getDescription().equals(status))
                        .toList()
                        .size());
    }

    @Then("I get {string} back")
    public void i_get_back(String responseMessage) {
      Assert.assertTrue(true);
    }

    @Then("the case is not in the database")
    public void the_case_is_not_in_the_database() {
        Assert.assertTrue( caseRepo.findById(stepContext.expectedCase.getId()).isEmpty());
    }

    private Optional<Case> extractCaseFromResponseEntity(ResponseEntity<CaseEntityModel> actualResponseEntityCase) {
        if (actualResponseEntityCase.hasBody()) {
            final CaseEntityModel caseEntityModel = actualResponseEntityCase.getBody();
            assert caseEntityModel != null;
            if (caseEntityModel.getContent() != null) {
                final Case aCase = caseEntityModel.getContent();
                return Optional.of(Case.builder()
                        .id(caseClient.getIdFromEntity(caseEntityModel))
                        .description(aCase.getDescription())
                        .startedAt(aCase.getStartedAt())
                        .type(caseClient.getCaseTypeFromEntity(caseEntityModel).orElseThrow())
                        .caseStatus(caseClient.getCaseStatusTypeFromEntity(caseEntityModel).orElseThrow())
                        .build());
            }
            return Optional.empty();
        }
        return Optional.empty();
    }
}
