package behaviorTests.steps;

import behaviorTests.CucumberSpringBootContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.erpmicroservices.peopleandorganizations.api.rest.models.Case;
import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseStatusType;
import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseType;
import org.erpmicroservices.peopleandorganizations.api.rest.repositories.*;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.erpmicroservices.peopleandorganizations.builders.DateTimeTestDataBuilder.zonedDateTimeNow;

public class CaseSteps extends CucumberSpringBootContext {

    private final List<Case> expectedCases = new ArrayList<>();
    private List<Case> actualCases = new ArrayList<>();

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

    @When("I search for all cases")
    public void i_search_for_all_cases() {
        actualCases = caseRepo.findAll();
    }

    @When("I search for cases of type {string}")
    public void i_search_for_cases_of_type(String caseTypeDescription) {
        actualCases = caseRepo.findAllByType_Description(caseTypeDescription);
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

    public CaseSteps(CaseStatusTypeRepo caseStatusTypeRepo, CaseTypeRepo caseTypeRepo, CaseRepo caseRepo, PartyTypeRepo partyTypeRepo, PartyRepo partyRepo, CaseRoleTypeRepo caseRoleTypeRepo, CaseRoleRepo caseRoleRepo, ContactMechanismTypeRepo contactMechanismTypeRepo, PartyRoleTypeRepo partyRoleTypeRepo, PartyRoleRepo partyRoleRepo, CommunicationEventStatusTypeRepo communicationEventStatusTypeRepo, CommunicationEventTypeRepo communicationEventTypeRepo, PartyRelationshipTypeRepo partyRelationshipTypeRepo, PartyRelationshipStatusTypeRepo partyRelationshipStatusTypeRepo, PriorityTypeRepo priorityTypeRepo, PartyRelationshipRepo partyRelationshipRepo, CommunicationEventRepo communicationEventRepo, FacilityRepo facilityRepo, FacilityTypeRepo facilityTypeRepo, FacilityRoleTypeRepo facilityRoleTypeRepo, FacilityRoleRepo facilityRoleRepo, FacilityContactMechanismRepo facilityContactMechanismRepo, ContactMechanismRepo contactMechanismRepo, GeographicBoundaryRepo geographicBoundaryRepo, GeographicBoundaryTypeRepo geographicBoundaryTypeRepo, ContactMechanismGeographicBoundaryRepo contactMechanismGeographicBoundaryRepo, PartyContactMechanismRepo partyContactMechanismRepo, PartyContactMechanismPurposeRepo partyContactMechanismPurposeRepo, PartyContactMechanismPurposeTypeRepo partyContactMechanismPurposeTypeRepo, CommunicationEventPurposeTypeRepo communicationEventPurposeTypeRepo, CommunicationEventRoleTypeRepo communicationEventRoleTypeRepo) {
        super(caseStatusTypeRepo, caseTypeRepo, caseRepo, partyTypeRepo, partyRepo, caseRoleTypeRepo, caseRoleRepo, contactMechanismTypeRepo, partyRoleTypeRepo, partyRoleRepo, communicationEventStatusTypeRepo, communicationEventTypeRepo, partyRelationshipTypeRepo, partyRelationshipStatusTypeRepo, priorityTypeRepo, partyRelationshipRepo, communicationEventRepo, facilityRepo, facilityTypeRepo, facilityRoleTypeRepo, facilityRoleRepo, facilityContactMechanismRepo, contactMechanismRepo, geographicBoundaryRepo, geographicBoundaryTypeRepo, contactMechanismGeographicBoundaryRepo, partyContactMechanismRepo, partyContactMechanismPurposeRepo, partyContactMechanismPurposeTypeRepo, communicationEventPurposeTypeRepo, communicationEventRoleTypeRepo);
    }
}
