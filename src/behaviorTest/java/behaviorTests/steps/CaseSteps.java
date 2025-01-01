package behaviorTests.steps;

import behaviorTests.CucumberSpringBootContext;
import behaviorTests.clients.CaseClient;
import behaviorTests.models.CaseCollectionEntityModel;
import behaviorTests.models.CaseEntityModel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.erpmicroservices.peopleandorganizations.api.rest.models.*;
import org.erpmicroservices.peopleandorganizations.api.rest.repositories.*;
import org.junit.Assert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.erpmicroservices.peopleandorganizations.builders.DateTimeTestDataBuilder.zonedDateTimeNow;

public class CaseSteps extends CucumberSpringBootContext {

    private final CaseClient caseClient;

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
        Assert.assertEquals("Did not get 200 response.  " + caseCollectionModelResponseEntity.getStatusCode(), HttpStatusCode.valueOf(200), caseCollectionModelResponseEntity.getStatusCode());
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
        stepContext.actualResponseEntityCase = caseClient.update(stepContext.expectedCase);
    }

    @When("I delete the case")
    public void i_delete_the_case() {
        stepContext.actualResponseEntityVoid = caseClient.delete(stepContext.expectedCase);
    }

    @When("I add the communication event to the case")
    public void i_add_the_communication_event_to_the_case() {
        stepContext.expectedCommunicationEvents.forEach(event -> {
            stepContext.expectedCase.addCommunicationEvent(event);
            caseClient.addCommunicationEventToCase(stepContext.expectedCase, event);
        });

    }

    @When("I add a party with case role {string} to the case")
    public void i_add_a_party_with_case_role_to_the_case(String caseRoleDescription) {
        final Page<CaseRoleType> caseRoleTypes = caseRoleTypeRepo.findByDescriptionContaining(caseRoleDescription, Pageable.unpaged());

        CaseRole caseRole = caseRoleRepo.save(CaseRole.builder()
                .fromDate(LocalDate.now())
                .party(stepContext.parties.getFirst())
                .type(caseRoleTypes.stream().toList().get(0))
                .build());
        caseClient.addCaseRole(stepContext.expectedCase, caseRole);
    }

    @Then("the operation was successful")
    public void the_operation_was_successful() {
        if (stepContext.actualResponseEntityVoid != null) {
            Assert.assertTrue("Response was supposed to be 2xx but was" + stepContext.actualResponseEntityVoid.getStatusCode(), stepContext.actualResponseEntityVoid.getStatusCode().is2xxSuccessful());
        }
        if (stepContext.actualResponseEntityCase != null) {
            Assert.assertTrue("Response was supposed to be 2xx but was" + stepContext.actualResponseEntityCase.getStatusCode(), stepContext.actualResponseEntityCase.getStatusCode().is2xxSuccessful());
        }
    }

    @Then("the case is in the database")
    public void the_case_is_in_the_database() {
        Case actualCase = extractCaseFromResponseEntity(stepContext.actualResponseEntityCase).orElseThrow();
        Assert.assertNotNull("Case a null id", actualCase.getId());
        Assert.assertEquals("Case descriptions do not match", stepContext.expectedCase.getDescription(), actualCase.getDescription());
        Assert.assertEquals("Case statuses are not the same", stepContext.expectedCase.getCaseStatus().getId(), actualCase.getCaseStatus().getId());
        Assert.assertEquals("Case started at timestamps are not the same", stepContext.expectedCase.getStartedAt(), actualCase.getStartedAt().withZoneSameInstant(stepContext.expectedCase.getStartedAt().getZone()));
        Assert.assertEquals("Case types are not the same", stepContext.expectedCase.getType().getId(), actualCase.getType().getId());
        final Optional<Case> caseInDb = caseRepo.findById(actualCase.getId());
        Assert.assertTrue("Case is not in database", caseInDb.isPresent());

        caseInDb.ifPresent(aCase -> {
            Assert.assertEquals("Case in database description is not correct", stepContext.expectedCase.getDescription(), aCase.getDescription());
            Assert.assertEquals("Case in database status is not correct", stepContext.expectedCase.getCaseStatus().getId(), aCase.getCaseStatus().getId());
            Assert.assertEquals("Case in database started at is not correct", stepContext.expectedCase.getStartedAt(), aCase.getStartedAt().withZoneSameInstant(stepContext.expectedCase.getStartedAt().getZone()));
            Assert.assertEquals("Case in database type is not correct", stepContext.expectedCase.getType().getId(), aCase.getType().getId());
        });


    }

    @Then("I get {int} cases")
    public void i_get_cases(Integer numberOfCases) {
        Assert.assertEquals("The number of expected cases is not correct", numberOfCases.longValue(), stepContext.actualCases.size());
    }

    @Then("{int} of them are cases of type {string}")
    public void of_them_are_cases_of_type(Integer numberOfCases, String caseType) {
        Assert.assertEquals(
                "The number of cases of type " + caseType + " is not correct",
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
        Assert.assertEquals("The number of cases in status" + status + " is not correct",
                numberOfCases.longValue(),
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
        Assert.assertTrue("The case is in the database", caseRepo.findById(Objects.requireNonNull(stepContext.expectedCase.getId())).isEmpty());
    }

    @Then("the case contains the communication event")
    public void the_case_contains_the_communication_event() {
        final List<CommunicationEvent> communicationEventList = communicationEventRepo.findAllByKase_Id(stepContext.expectedCase.getId());
        Assert.assertFalse("The list of communicationn events in the database is empty",
                communicationEventList.isEmpty());
        communicationEventList.forEach(communicationEvent -> Assert.assertTrue(
                stepContext.expectedCommunicationEvents.stream()
                        .map(CommunicationEvent::getId)
                        .toList()
                        .contains(communicationEvent.getId())));

    }

    @Then("the case has {int} roles")
    public void the_case_has_roles(long numberOfRoles) {
        Assert.assertEquals("The case does not have the right amount of roles", numberOfRoles, stepContext.actualCaseRoles.getNumber());
    }

    @Then("the {int} roles have type {string}")
    public void the_roles_have_type(long roleCount, String caseRoleTypeDescription) {
        Assert.assertEquals("There are not enough roles for case with type of " + caseRoleTypeDescription,
                roleCount,
                stepContext.actualCaseRoles.get()
                        .filter(caseRole ->
                                caseRole.getType().getDescription().equals(caseRoleTypeDescription))
                        .count());
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
