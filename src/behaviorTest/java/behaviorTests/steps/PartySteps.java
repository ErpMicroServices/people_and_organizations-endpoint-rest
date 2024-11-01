package behaviorTests.steps;

import behaviorTests.CucumberSpringBootContext;
import io.cucumber.java.en.Given;
import org.erpmicroservices.peopleandorganizations.api.rest.models.*;
import org.erpmicroservices.peopleandorganizations.api.rest.repositories.*;
import org.junit.Assert;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

public class PartySteps extends CucumberSpringBootContext {

    public PartySteps(StepContext stepContext, RestTemplate template, CaseStatusTypeRepo caseStatusTypeRepo, CaseTypeRepo caseTypeRepo, CaseRepo caseRepo, PartyTypeRepo partyTypeRepo, PartyRepo partyRepo, CaseRoleTypeRepo caseRoleTypeRepo, CaseRoleRepo caseRoleRepo, ContactMechanismTypeRepo contactMechanismTypeRepo, PartyRoleTypeRepo partyRoleTypeRepo, PartyRoleRepo partyRoleRepo, CommunicationEventStatusTypeRepo communicationEventStatusTypeRepo, CommunicationEventTypeRepo communicationEventTypeRepo, PartyRelationshipTypeRepo partyRelationshipTypeRepo, PartyRelationshipStatusTypeRepo partyRelationshipStatusTypeRepo, PriorityTypeRepo priorityTypeRepo, PartyRelationshipRepo partyRelationshipRepo, CommunicationEventRepo communicationEventRepo, FacilityRepo facilityRepo, FacilityTypeRepo facilityTypeRepo, FacilityRoleTypeRepo facilityRoleTypeRepo, FacilityRoleRepo facilityRoleRepo, FacilityContactMechanismRepo facilityContactMechanismRepo, ContactMechanismRepo contactMechanismRepo, GeographicBoundaryRepo geographicBoundaryRepo, GeographicBoundaryTypeRepo geographicBoundaryTypeRepo, ContactMechanismGeographicBoundaryRepo contactMechanismGeographicBoundaryRepo, PartyContactMechanismRepo partyContactMechanismRepo, PartyContactMechanismPurposeRepo partyContactMechanismPurposeRepo, PartyContactMechanismPurposeTypeRepo partyContactMechanismPurposeTypeRepo, CommunicationEventPurposeTypeRepo communicationEventPurposeTypeRepo, CommunicationEventRoleTypeRepo communicationEventRoleTypeRepo) {
        super(stepContext, template, caseStatusTypeRepo, caseTypeRepo, caseRepo, partyTypeRepo, partyRepo, caseRoleTypeRepo, caseRoleRepo, contactMechanismTypeRepo, partyRoleTypeRepo, partyRoleRepo, communicationEventStatusTypeRepo, communicationEventTypeRepo, partyRelationshipTypeRepo, partyRelationshipStatusTypeRepo, priorityTypeRepo, partyRelationshipRepo, communicationEventRepo, facilityRepo, facilityTypeRepo, facilityRoleTypeRepo, facilityRoleRepo, facilityContactMechanismRepo, contactMechanismRepo, geographicBoundaryRepo, geographicBoundaryTypeRepo, contactMechanismGeographicBoundaryRepo, partyContactMechanismRepo, partyContactMechanismPurposeRepo, partyContactMechanismPurposeTypeRepo, communicationEventPurposeTypeRepo, communicationEventRoleTypeRepo);
    }

    @Given("there are {int} parties with a type of {string} in the database")
    public void there_are_parties_with_a_type_of_in_the_database(Integer numberOfPartiesToCreate, String partyTypeDescription) {
        createPartiesOfType(numberOfPartiesToCreate, partyTypeDescription);
    }

    private void createPartiesOfType(Integer numberOfPartiesToCreate, String partyTypeDescription) {
        final PartyType partyType = partyTypeRepo.findByDescription(partyTypeDescription);
        for (int i = 0; i < numberOfPartiesToCreate; i++) {
            final Party party = Party.builder()
                    .type(partyType)
                    .comment("""
                            party number %d
                            """.formatted(i))
                    .build();
            final Party saved = partyRepo.save(party);
            stepContext.parties.add(saved);
        }
    }

    @Given("party {int} has a party role of {string}")
    public void party_has_a_party_role_of(Integer index, String partyRoleTypeDescription) {
        final PartyRoleType partyRoleType = partyRoleTypeRepo.findByDescription(partyRoleTypeDescription);
        final PartyRole partyRole = PartyRole.builder()
                .type(partyRoleType)
                .fromDate(LocalDate.now())
                .build();
        Party party = stepContext.parties.get(index - 1);
        party.addRole(partyRole);
        Party p2 = partyRepo.save(party);
        stepContext.parties.set(index - 1, p2);
    }

    @Given("a party relationship of type {string} between party role {string} and party role {string} in status {string} with priority {string}")
    public void a_party_relationship_of_type_between_party_role_and_party_role_in_status_with_priority(String relationshipTypeDescription, String partyRoleFromWithTypeDescription, String partyRoleToWithTypeDescription, String statusTypeDescription, String priorityTypeDescription) {
        final PartyRelationshipType partyRelationshipType = partyRelationshipTypeRepo.findByDescription(relationshipTypeDescription);
        final PartyRole fromPartyRole = partyRoleRepo.findAllByType_Description(partyRoleFromWithTypeDescription, stepContext.pageable).stream().findFirst().orElseThrow();
        final PartyRole toPartyRole = partyRoleRepo.findAllByType_Description(partyRoleToWithTypeDescription, stepContext.pageable).stream().findFirst().orElseThrow();
        final PartyRelationshipStatusType partyRelationshipStatusType = partyRelationshipStatusTypeRepo.findByDescription(statusTypeDescription);
        final PriorityType priorityType = priorityTypeRepo.findByDescription(priorityTypeDescription);
        final PartyRelationship partyRelationship = PartyRelationship.builder()
                .fromPartyRole(fromPartyRole)
                .toPartyRole(toPartyRole)
                .type(partyRelationshipType)
                .priority(priorityType)
                .status(partyRelationshipStatusType)
                .comment("Test party relationship")
                .fromDate(LocalDate.now())
                .build();
        stepContext.expectedPartyRelationship = partyRelationshipRepo.save(partyRelationship);
    }

    @Given("a party with a comment of {string} and a type of {string} is in the database")
    public void a_party_with_a_comment_of_and_a_type_of_is_in_the_database(String partyComment, String partyTypeDescription) {
        stepContext.partyTypes.stream()
                .filter(pt ->
                        pt.getDescription().equals(partyTypeDescription))
                .findFirst()
                .ifPresentOrElse(partyType ->
                                stepContext.parties.add(
                                        partyRepo.save(
                                                Party.builder()
                                                        .comment(partyComment)
                                                        .type(partyType)
                                                        .build())),
                        () ->
                                Assert.fail("Party type: " + partyTypeDescription + " was not created."));
    }

}
