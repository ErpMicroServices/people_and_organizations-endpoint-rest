package behaviorTests.steps;

import behaviorTests.CucumberSpringBootContext;
import behaviorTests.clients.CommunicationEventClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEvent;
import org.erpmicroservices.peopleandorganizations.api.rest.models.PartyRelationship;
import org.erpmicroservices.peopleandorganizations.api.rest.models.PartyRelationshipStatusType;
import org.erpmicroservices.peopleandorganizations.api.rest.models.PriorityType;
import org.erpmicroservices.peopleandorganizations.api.rest.repositories.*;
import org.junit.Assert;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;

public class CaseEventSteps extends CucumberSpringBootContext {

    protected final CommunicationEventClient communicationEventClient;

    public CaseEventSteps(StepContext stepContext, RestTemplate template, CommunicationEventClient communicationEventClient, CaseStatusTypeRepo caseStatusTypeRepo, CaseTypeRepo caseTypeRepo, CaseRepo caseRepo, PartyTypeRepo partyTypeRepo, PartyRepo partyRepo, CaseRoleTypeRepo caseRoleTypeRepo, CaseRoleRepo caseRoleRepo, ContactMechanismTypeRepo contactMechanismTypeRepo, PartyRoleTypeRepo partyRoleTypeRepo, PartyRoleRepo partyRoleRepo, CommunicationEventStatusTypeRepo communicationEventStatusTypeRepo, CommunicationEventTypeRepo communicationEventTypeRepo, PartyRelationshipTypeRepo partyRelationshipTypeRepo, PartyRelationshipStatusTypeRepo partyRelationshipStatusTypeRepo, PriorityTypeRepo priorityTypeRepo, PartyRelationshipRepo partyRelationshipRepo, CommunicationEventRepo communicationEventRepo, FacilityRepo facilityRepo, FacilityTypeRepo facilityTypeRepo, FacilityRoleTypeRepo facilityRoleTypeRepo, FacilityRoleRepo facilityRoleRepo, FacilityContactMechanismRepo facilityContactMechanismRepo, ContactMechanismRepo contactMechanismRepo, GeographicBoundaryRepo geographicBoundaryRepo, GeographicBoundaryTypeRepo geographicBoundaryTypeRepo, ContactMechanismGeographicBoundaryRepo contactMechanismGeographicBoundaryRepo, PartyContactMechanismRepo partyContactMechanismRepo, PartyContactMechanismPurposeRepo partyContactMechanismPurposeRepo, PartyContactMechanismPurposeTypeRepo partyContactMechanismPurposeTypeRepo, CommunicationEventPurposeTypeRepo communicationEventPurposeTypeRepo, CommunicationEventRoleTypeRepo communicationEventRoleTypeRepo) {
        super(stepContext, template, caseStatusTypeRepo, caseTypeRepo, caseRepo, partyTypeRepo, partyRepo, caseRoleTypeRepo, caseRoleRepo, contactMechanismTypeRepo, partyRoleTypeRepo, partyRoleRepo, communicationEventStatusTypeRepo, communicationEventTypeRepo, partyRelationshipTypeRepo, partyRelationshipStatusTypeRepo, priorityTypeRepo, partyRelationshipRepo, communicationEventRepo, facilityRepo, facilityTypeRepo, facilityRoleTypeRepo, facilityRoleRepo, facilityContactMechanismRepo, contactMechanismRepo, geographicBoundaryRepo, geographicBoundaryTypeRepo, contactMechanismGeographicBoundaryRepo, partyContactMechanismRepo, partyContactMechanismPurposeRepo, partyContactMechanismPurposeTypeRepo, communicationEventPurposeTypeRepo, communicationEventRoleTypeRepo);
        this.communicationEventClient = communicationEventClient;
    }

    @Given("a communication event with a note of {string}")
    public void a_communication_event_with_a_note_of(String note) {
        stepContext.communicatoinEventBuilder = CommunicationEvent.builder();
        stepContext.communicatoinEventBuilder.note(note);
    }

    @Given("a communication event is for a relationship between party {int} and party {int}")
    public void a_communication_event_is_for_a_relationship_between_party_and_party(Integer partyX, Integer partyY) {
        final PartyRelationshipStatusType partyRelationshipStatusType = partyRelationshipStatusTypeRepo.findAll().getFirst();
        final PriorityType priorityType = priorityTypeRepo.findAll().getFirst();
        final PartyRelationship partyRelationship = PartyRelationship.builder()
                .fromPartyRole(stepContext.parties.get(partyX - 1).getRoles().getFirst())
                .toPartyRole(stepContext.parties.get(partyY - 1).getRoles().getFirst())
                .type(stepContext.partyRelationshipTypes.getFirst())
                .status(partyRelationshipStatusType)
                .priority(priorityType)
                .fromDate(LocalDate.now())
                .build();
        final PartyRelationship relationship = partyRelationshipRepo.save(partyRelationship);
        stepContext.communicatoinEventBuilder.relationship(relationship);

    }

    @Given("a communication event status is {string}")
    public void a_communication_event_status_is(String communicationEventStatusDescription) {
        stepContext
                .communicatoinEventBuilder
                .statusType(stepContext.communicationEventStatusTypes.stream()
                        .filter(t ->
                                t.getDescription().equals(communicationEventStatusDescription))
                        .findFirst()
                        .orElseThrow());
    }

    @Given("a communication event contact mechanism type is {string}")
    public void a_communication_event_contact_mechanism_type_is(String communicationEventContactMechanismTypeDescription) {
        stepContext.communicatoinEventBuilder.contactMechanismType(stepContext
                .contactMechanismTypes.stream()
                .filter(t ->
                        t.getDescription().equals(communicationEventContactMechanismTypeDescription))
                .findFirst()
                .orElseThrow());
    }

    @Given("a communication event has a type of {string}")
    public void a_communication_event_has_a_type_of(String typeOfDescription) {
        stepContext.communicatoinEventBuilder.type(
                stepContext
                        .communicationEventTypes.stream()
                        .filter(t -> t.getDescription().equals(typeOfDescription))
                        .findFirst()
                        .orElseThrow()
        );
    }

    @Given("a communication event starts on {string} at {string}")
    public void a_communication_event_starts_on_at(String date, String time) {
        stepContext.communicatoinEventBuilder.started(ZonedDateTime.parse(date + "T" + time));
    }

    @Given("a communication event ends on {string} at {string}")
    public void a_communication_event_ends_on_at(String date, String time) {
        stepContext.communicatoinEventBuilder.ended(ZonedDateTime.parse(date + "T" + time));
    }


    @When("I create a communication event")
    public void i_create_a_communication_event() {
        final CommunicationEvent communicationEvent = stepContext.communicatoinEventBuilder.build();

        stepContext.expectedCommunicationEvents.add(communicationEvent);
        stepContext.actualCommunicationEventEntityModel = communicationEventClient.create(communicationEvent);

    }

    @Then("I find the communication event in the database")
    public void i_find_the_communication_event_in_the_database() {
        Assert.assertNotNull("Actual communication event entity model is null, and shouldn't be", stepContext.actualCommunicationEventEntityModel);
        Assert.assertNotNull("Actual communication event entity model body is null, and shouldn't be", stepContext.actualCommunicationEventEntityModel.getBody());
        final Optional<CommunicationEvent> communicationEvent = communicationEventRepo.findById(communicationEventClient.getIdFromEntity(stepContext.actualCommunicationEventEntityModel.getBody()));
        Assert.assertTrue("Expected the communication event to be in the database.", communicationEvent.isPresent());
    }
}
