package behaviorTests.steps;

import behaviorTests.CucumberSpringBootContext;
import behaviorTests.clients.CommunicationEventClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.erpmicroservices.peopleandorganizations.api.rest.models.*;
import org.erpmicroservices.peopleandorganizations.api.rest.repositories.*;
import org.junit.Assert;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;

public class CommunicationEventSteps extends CucumberSpringBootContext {
    protected final CommunicationEventClient communicationEventClient;

    public CommunicationEventSteps(StepContext stepContext, RestTemplate template, CommunicationEventClient communicationEventClient, CaseStatusTypeRepo caseStatusTypeRepo, CaseTypeRepo caseTypeRepo, CaseRepo caseRepo, PartyTypeRepo partyTypeRepo, PartyRepo partyRepo, CaseRoleTypeRepo caseRoleTypeRepo, CaseRoleRepo caseRoleRepo, ContactMechanismTypeRepo contactMechanismTypeRepo, PartyRoleTypeRepo partyRoleTypeRepo, PartyRoleRepo partyRoleRepo, CommunicationEventStatusTypeRepo communicationEventStatusTypeRepo, CommunicationEventTypeRepo communicationEventTypeRepo, PartyRelationshipTypeRepo partyRelationshipTypeRepo, PartyRelationshipStatusTypeRepo partyRelationshipStatusTypeRepo, PriorityTypeRepo priorityTypeRepo, PartyRelationshipRepo partyRelationshipRepo, CommunicationEventRepo communicationEventRepo, FacilityRepo facilityRepo, FacilityTypeRepo facilityTypeRepo, FacilityRoleTypeRepo facilityRoleTypeRepo, FacilityRoleRepo facilityRoleRepo, FacilityContactMechanismRepo facilityContactMechanismRepo, ContactMechanismRepo contactMechanismRepo, GeographicBoundaryRepo geographicBoundaryRepo, GeographicBoundaryTypeRepo geographicBoundaryTypeRepo, ContactMechanismGeographicBoundaryRepo contactMechanismGeographicBoundaryRepo, PartyContactMechanismRepo partyContactMechanismRepo, PartyContactMechanismPurposeRepo partyContactMechanismPurposeRepo, PartyContactMechanismPurposeTypeRepo partyContactMechanismPurposeTypeRepo, CommunicationEventPurposeTypeRepo communicationEventPurposeTypeRepo, CommunicationEventRoleTypeRepo communicationEventRoleTypeRepo) {
        super(stepContext, template, caseStatusTypeRepo, caseTypeRepo, caseRepo, partyTypeRepo, partyRepo, caseRoleTypeRepo, caseRoleRepo, contactMechanismTypeRepo, partyRoleTypeRepo, partyRoleRepo, communicationEventStatusTypeRepo, communicationEventTypeRepo, partyRelationshipTypeRepo, partyRelationshipStatusTypeRepo, priorityTypeRepo, partyRelationshipRepo, communicationEventRepo, facilityRepo, facilityTypeRepo, facilityRoleTypeRepo, facilityRoleRepo, facilityContactMechanismRepo, contactMechanismRepo, geographicBoundaryRepo, geographicBoundaryTypeRepo, contactMechanismGeographicBoundaryRepo, partyContactMechanismRepo, partyContactMechanismPurposeRepo, partyContactMechanismPurposeTypeRepo, communicationEventPurposeTypeRepo, communicationEventRoleTypeRepo);
        this.communicationEventClient = communicationEventClient;
    }

    @Given("communication events:")
    public void communication_events(io.cucumber.datatable.DataTable dataTable) {
        dataTable.asMaps()
                .forEach(row -> {
                    final ContactMechanismType contactMechanismType = contactMechanismTypeRepo.findByDescription(row.get("Contact Mechanism Type"));
                    final CommunicationEventStatusType status = communicationEventStatusTypeRepo.findByDescription(row.get("Status"));
                    final CommunicationEventType type = communicationEventTypeRepo.findByDescription(row.get("Type"));
                    stepContext.expectedCommunicationEvents.
                            add(
                                    communicationEventRepo.save(
                                            CommunicationEvent.builder()
                                                    .contactMechanismType(contactMechanismType)
                                                    .note(row.get("Note"))
                                                    .relationship(stepContext.expectedPartyRelationship)
                                                    .started(ZonedDateTime.parse(row.get("Started At")))
                                                    .ended(ZonedDateTime.parse(row.get("Ended At")))
                                                    .statusType(status)
                                                    .type(type)
                                                    .build()));
                });
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

    @Given("the communication event is in the database")
    public void the_communication_event_is_in_the_database() {
        final CommunicationEvent communicationEvent = stepContext.communicatoinEventBuilder.build();
        final CommunicationEvent saved = communicationEventRepo.save(communicationEvent);
        stepContext.expectedCommunicationEvents.add(saved);
    }
    @When("I search for communication events that occurred between {string} and {string} on {string}")
    public void i_search_for_communication_events_that_occurred_between_and_on(String fromTime, String thruTime, String date) {
        ZonedDateTime fromTimestamp = ZonedDateTime.parse(date + "T" + fromTime);
        ZonedDateTime thruTimestamp = ZonedDateTime.parse(date + "T" + thruTime);
        stepContext.actualCommunicationEventListEntityModel = communicationEventClient.findAllEventsBetween( fromTimestamp, thruTimestamp);
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

    @Then("the communication event of type {string} is found")
    public void the_communication_event_of_type_is_found(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the communication event of type {string} is not found")
    public void the_communication_event_of_type_is_not_found(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
