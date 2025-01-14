package behaviorTests.steps;

import behaviorTests.CucumberSpringBootContext;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import org.erpmicroservices.peopleandorganizations.api.rest.models.*;
import org.erpmicroservices.peopleandorganizations.api.rest.repositories.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class CommonSteps extends CucumberSpringBootContext {


    public CommonSteps(StepContext stepContext, RestTemplate template, CaseTypeRepo caseTypeRepo, CaseStatusTypeRepo caseStatusTypeRepo, PartyContactMechanismRepo partyContactMechanismRepo, ContactMechanismRepo contactMechanismRepo, ContactMechanismGeographicBoundaryRepo contactMechanismGeographicBoundaryRepo, CommunicationEventPurposeTypeRepo communicationEventPurposeTypeRepo, CaseRepo caseRepo, PartyTypeRepo partyTypeRepo, PartyRelationshipStatusTypeRepo partyRelationshipStatusTypeRepo, FacilityRoleRepo facilityRoleRepo, PartyRepo partyRepo, CaseRoleTypeRepo caseRoleTypeRepo, PartyRelationshipTypeRepo partyRelationshipTypeRepo, CommunicationEventRepo communicationEventRepo, FacilityRoleTypeRepo facilityRoleTypeRepo, GeographicBoundaryRepo geographicBoundaryRepo, CommunicationEventTypeRepo communicationEventTypeRepo, CaseRoleRepo caseRoleRepo, PartyContactMechanismPurposeRepo partyContactMechanismPurposeRepo, ContactMechanismTypeRepo contactMechanismTypeRepo, FacilityContactMechanismRepo facilityContactMechanismRepo, PartyRoleTypeRepo partyRoleTypeRepo, PartyRoleRepo partyRoleRepo, FacilityTypeRepo facilityTypeRepo, GeographicBoundaryTypeRepo geographicBoundaryTypeRepo, CommunicationEventStatusTypeRepo communicationEventStatusTypeRepo, PartyRelationshipRepo partyRelationshipRepo, PartyContactMechanismPurposeTypeRepo partyContactMechanismPurposeTypeRepo, CommunicationEventRoleTypeRepo communicationEventRoleTypeRepo, PriorityTypeRepo priorityTypeRepo, FacilityRepo facilityRepo) {
        super(stepContext, template, caseStatusTypeRepo, caseTypeRepo, caseRepo, partyTypeRepo, partyRepo, caseRoleTypeRepo, caseRoleRepo, contactMechanismTypeRepo, partyRoleTypeRepo, partyRoleRepo, communicationEventStatusTypeRepo, communicationEventTypeRepo, partyRelationshipTypeRepo, partyRelationshipStatusTypeRepo, priorityTypeRepo, partyRelationshipRepo, communicationEventRepo, facilityRepo, facilityTypeRepo, facilityRoleTypeRepo, facilityRoleRepo, facilityContactMechanismRepo, contactMechanismRepo, geographicBoundaryRepo, geographicBoundaryTypeRepo, contactMechanismGeographicBoundaryRepo, partyContactMechanismRepo, partyContactMechanismPurposeRepo, partyContactMechanismPurposeTypeRepo, communicationEventPurposeTypeRepo, communicationEventRoleTypeRepo);
    }

    @BeforeAll
    public static void setupWorld() {
//        postgreSQLContainer.start();
    }

    @AfterAll
    public static void tearDownWorld() {
//        postgreSQLContainer.stop();
    }


    @Before
    public void setupTheScenario() {
        theDatabaseIsEmpty();
        // For some reason the stepcontext isn't getting initialized by spring.
        stepContext.caseStatusTypes = new ArrayList<>();
        stepContext.caseTypes = new ArrayList<>();
        stepContext.communicationEventRoleTypes = new ArrayList<>();
        stepContext.communicationEventStatusTypes = new ArrayList<>();
        stepContext.communicationEventTypes = new ArrayList<>();
        stepContext.contactMechanismTypes = new ArrayList<>();
        stepContext.partyTypes = new ArrayList<>();
        stepContext.partyRoleTypes = new ArrayList<>();
        stepContext.partyRelationshipTypes = new ArrayList<>();
        stepContext.partyRelationshipStatusTypes = new ArrayList<>();
        stepContext.parties = new ArrayList<>();
        stepContext.priorityTypes = new ArrayList<>();
        stepContext.expectedCases = new ArrayList<>();
        stepContext.expectedCase = new Case();
        stepContext.caseRoleTypes = new ArrayList<>();
        stepContext.expectedCommunicationEvents = new ArrayList<>();
    }

    @Given("the following types:")
    public void the_following_types(io.cucumber.datatable.DataTable dataTable) {
        final List<List<String>> dataTableLists = dataTable.asLists();
        dataTableLists
                .forEach(row -> {
                    switch (row.get(0)) {
                        case "case" -> {
                            stepContext
                                    .caseTypes.add(caseTypeRepo.save(CaseType.builder()
                                            .description(row.get(1))
                                            .build()
                                    ));
                        }
                        case "case role" -> {
                            stepContext
                                    .caseRoleTypes.add(caseRoleTypeRepo.save(CaseRoleType.builder()
                                            .description(row.get(1))
                                            .build()));
                        }
                        case "case status" -> {
                            final CaseStatusType caseStatusType = caseStatusTypeRepo.save(CaseStatusType.builder()
                                    .description(row.get(1))
                                    .build()
                            );
                            stepContext.caseStatusTypes.add(caseStatusType);
                        }
                        case "party" -> stepContext
                                .partyTypes.add(partyTypeRepo.save(PartyType.builder()
                                        .description(row.get(1))
                                        .build()));
                        case "communication event" -> stepContext
                                .communicationEventTypes.add(communicationEventTypeRepo.save(CommunicationEventType.builder()
                                        .description(row.get(1))
                                        .build()));
                        case "communication event role" -> stepContext
                                .communicationEventRoleTypes.add(communicationEventRoleTypeRepo.save(CommunicationEventRoleType.builder()
                                        .description(row.get(1))
                                        .build()));
                        case "communication event status" -> stepContext
                                .communicationEventStatusTypes.add(communicationEventStatusTypeRepo.save(CommunicationEventStatusType.builder()
                                        .description(row.get(1))
                                        .build()));
                        case "contact mechanism" -> stepContext
                                .contactMechanismTypes.add(contactMechanismTypeRepo.save(ContactMechanismType.builder()
                                        .description(row.get(1))
                                        .build()));

                        case "party role" -> stepContext
                                .partyRoleTypes.add(partyRoleTypeRepo.save(PartyRoleType.builder()
                                        .description(row.get(1))
                                        .build()));

                        case "party relationship" -> stepContext
                                .partyRelationshipTypes.add(partyRelationshipTypeRepo.save(PartyRelationshipType.builder()
                                        .description(row.get(1))
                                        .build()));

                        case "party relationship status" -> stepContext
                                .partyRelationshipStatusTypes.add(partyRelationshipStatusTypeRepo.save(PartyRelationshipStatusType.builder()
                                        .description(row.get(1))
                                        .build()));

                        case "priority" -> stepContext
                                .priorityTypes.add(priorityTypeRepo.save(PriorityType.builder()
                                        .description(row.get(1))
                                        .build()));

                        default -> fail("Unknown type: " + row);

                    }
                });
    }

    private void theDatabaseIsEmpty() {
        communicationEventRepo.deleteAll();
        communicationEventTypeRepo.deleteAll();
        partyRelationshipRepo.deleteAll();
        partyRoleRepo.deleteAll();
        caseRoleRepo.deleteAll();
        facilityRoleRepo.deleteAll();
        facilityContactMechanismRepo.deleteAll();
        partyContactMechanismRepo.deleteAll();
        contactMechanismGeographicBoundaryRepo.deleteAll();
        contactMechanismRepo.deleteAll();
        geographicBoundaryRepo.deleteAll();


        partyRepo.deleteAll();
        caseRepo.deleteAll();
        facilityRepo.deleteAll();

        caseRoleTypeRepo.deleteAll(caseRoleTypeRepo.findCaseRoleTypeByParentIdIsNotNull(Pageable.unpaged()).stream().toList());
        caseRoleTypeRepo.deleteAll();
        caseStatusTypeRepo.deleteAll(caseStatusTypeRepo.findCaseStatusTypeByParentIdIsNotNull(Pageable.unpaged()).stream().toList());
        caseStatusTypeRepo.deleteAll();

        caseTypeRepo.deleteAll(caseTypeRepo.findCaseTypeByParentIdIsNotNull(Pageable.unpaged()).stream().toList());
        caseTypeRepo.deleteAll();

        partyTypeRepo.findPartyTypesByParentIdIsNull(Pageable.unpaged()).forEach(this::deletePartyTypeChildren);
        partyTypeRepo.deleteAll();

        partyRelationshipTypeRepo.deleteAll();
        priorityTypeRepo.deleteAll();
        partyRelationshipStatusTypeRepo.deleteAll();
        partyRelationshipTypeRepo.deleteAll();

        partyRoleTypeRepo.findPartyRoleTypesByParentIdIsNull(Pageable.unpaged()).forEach(prt -> {
            deletePartyRoleTypeChildren(prt);
            partyRoleTypeRepo.delete(prt);
        });
        partyRoleTypeRepo.deleteAll();

        communicationEventStatusTypeRepo.findCommunicationEventStatusTypeByParentIdIsNull(Pageable.unpaged()).forEach(root -> {
            deleteCommunicationEventStatusTypeChildren(root);
            communicationEventStatusTypeRepo.delete(root);
        });

        contactMechanismTypeRepo.deleteAll();
        facilityRoleTypeRepo.deleteAll();
        facilityTypeRepo.deleteAll();
        geographicBoundaryTypeRepo.deleteAll();
        partyContactMechanismPurposeRepo.deleteAll();
        communicationEventPurposeTypeRepo.deleteAll(communicationEventPurposeTypeRepo.findCommunicationEventPurposeTypeByParentIdIsNotNull(Pageable.unpaged()).stream().toList());
        communicationEventPurposeTypeRepo.deleteAll();
        communicationEventRoleTypeRepo.deleteAll(communicationEventRoleTypeRepo.findCommunicationEventRoleTypeByParentIdIsNotNull(Pageable.unpaged()).stream().toList());
        communicationEventRoleTypeRepo.deleteAll();
    }

    private void deletePartyTypeChildren(PartyType root) {
        partyTypeRepo.findPartyTypesByParentId(root.getId(), Pageable.unpaged()).forEach(this::deletePartyTypeChildren);
        partyTypeRepo.delete(root);
    }

    private void deleteCommunicationEventStatusTypeChildren(CommunicationEventStatusType root) {
        if (root.getParent() != null) {
            communicationEventStatusTypeRepo.findCommunicationEventStatusTypeByParentId(root.getParent().getId(), Pageable.unpaged()).stream()
                    .forEach(this::deleteCommunicationEventStatusTypeChildren);
        }
        communicationEventStatusTypeRepo.delete(root);
    }

    private void deletePartyRoleTypeChildren(PartyRoleType root) {
        partyRoleTypeRepo.findPartyRoleTypesByParentId(root.getId(), Pageable.unpaged()).stream()
                .forEach(this::deletePartyRoleTypeChildren);
        partyRoleTypeRepo.delete(root);
    }

}
