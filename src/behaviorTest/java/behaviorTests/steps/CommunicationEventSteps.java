package behaviorTests.steps;

import behaviorTests.CucumberSpringBootContext;
import io.cucumber.java.en.Given;
import org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEvent;
import org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEventStatusType;
import org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEventType;
import org.erpmicroservices.peopleandorganizations.api.rest.models.ContactMechanismType;
import org.erpmicroservices.peopleandorganizations.api.rest.repositories.*;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;

public class CommunicationEventSteps extends CucumberSpringBootContext {
    public CommunicationEventSteps(StepContext stepContext, RestTemplate template, CaseStatusTypeRepo caseStatusTypeRepo, CaseTypeRepo caseTypeRepo, CaseRepo caseRepo, PartyTypeRepo partyTypeRepo, PartyRepo partyRepo, CaseRoleTypeRepo caseRoleTypeRepo, CaseRoleRepo caseRoleRepo, ContactMechanismTypeRepo contactMechanismTypeRepo, PartyRoleTypeRepo partyRoleTypeRepo, PartyRoleRepo partyRoleRepo, CommunicationEventStatusTypeRepo communicationEventStatusTypeRepo, CommunicationEventTypeRepo communicationEventTypeRepo, PartyRelationshipTypeRepo partyRelationshipTypeRepo, PartyRelationshipStatusTypeRepo partyRelationshipStatusTypeRepo, PriorityTypeRepo priorityTypeRepo, PartyRelationshipRepo partyRelationshipRepo, CommunicationEventRepo communicationEventRepo, FacilityRepo facilityRepo, FacilityTypeRepo facilityTypeRepo, FacilityRoleTypeRepo facilityRoleTypeRepo, FacilityRoleRepo facilityRoleRepo, FacilityContactMechanismRepo facilityContactMechanismRepo, ContactMechanismRepo contactMechanismRepo, GeographicBoundaryRepo geographicBoundaryRepo, GeographicBoundaryTypeRepo geographicBoundaryTypeRepo, ContactMechanismGeographicBoundaryRepo contactMechanismGeographicBoundaryRepo, PartyContactMechanismRepo partyContactMechanismRepo, PartyContactMechanismPurposeRepo partyContactMechanismPurposeRepo, PartyContactMechanismPurposeTypeRepo partyContactMechanismPurposeTypeRepo, CommunicationEventPurposeTypeRepo communicationEventPurposeTypeRepo, CommunicationEventRoleTypeRepo communicationEventRoleTypeRepo) {
        super(stepContext, template, caseStatusTypeRepo, caseTypeRepo, caseRepo, partyTypeRepo, partyRepo, caseRoleTypeRepo, caseRoleRepo, contactMechanismTypeRepo, partyRoleTypeRepo, partyRoleRepo, communicationEventStatusTypeRepo, communicationEventTypeRepo, partyRelationshipTypeRepo, partyRelationshipStatusTypeRepo, priorityTypeRepo, partyRelationshipRepo, communicationEventRepo, facilityRepo, facilityTypeRepo, facilityRoleTypeRepo, facilityRoleRepo, facilityContactMechanismRepo, contactMechanismRepo, geographicBoundaryRepo, geographicBoundaryTypeRepo, contactMechanismGeographicBoundaryRepo, partyContactMechanismRepo, partyContactMechanismPurposeRepo, partyContactMechanismPurposeTypeRepo, communicationEventPurposeTypeRepo, communicationEventRoleTypeRepo);
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
}
