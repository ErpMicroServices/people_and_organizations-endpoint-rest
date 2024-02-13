package behaviorTests.steps;

import behaviorTests.CucumberSpringBootContext;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import org.erpmicroservices.peopleandorganizations.api.rest.models.*;
import org.erpmicroservices.peopleandorganizations.api.rest.repositories.*;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class Common extends CucumberSpringBootContext {
    private final List<CaseType> caseTypes = new ArrayList<>();


    public Common(CaseTypeRepo caseTypeRepo, CaseStatusTypeRepo caseStatusTypeRepo, PartyContactMechanismRepo partyContactMechanismRepo, ContactMechanismRepo contactMechanismRepo, ContactMechanismGeographicBoundaryRepo contactMechanismGeographicBoundaryRepo, CommunicationEventPurposeTypeRepo communicationEventPurposeTypeRepo, CaseRepo caseRepo, PartyTypeRepo partyTypeRepo, PartyRelationshipStatusTypeRepo partyRelationshipStatusTypeRepo, FacilityRoleRepo facilityRoleRepo, PartyRepo partyRepo, CaseRoleTypeRepo caseRoleTypeRepo, PartyRelationshipTypeRepo partyRelationshipTypeRepo, CommunicationEventRepo communicationEventRepo, FacilityRoleTypeRepo facilityRoleTypeRepo, GeographicBoundaryRepo geographicBoundaryRepo, CommunicationEventTypeRepo communicationEventTypeRepo, CaseRoleRepo caseRoleRepo, PartyContactMechanismPurposeRepo partyContactMechanismPurposeRepo, ContactMechanismTypeRepo contactMechanismTypeRepo, FacilityContactMechanismRepo facilityContactMechanismRepo, PartyRoleTypeRepo partyRoleTypeRepo, PartyRoleRepo partyRoleRepo, FacilityTypeRepo facilityTypeRepo, GeographicBoundaryTypeRepo geographicBoundaryTypeRepo, CommunicationEventStatusTypeRepo communicationEventStatusTypeRepo, PartyRelationshipRepo partyRelationshipRepo, PartyContactMechanismPurposeTypeRepo partyContactMechanismPurposeTypeRepo, CommunicationEventRoleTypeRepo communicationEventRoleTypeRepo, PriorityTypeRepo priorityTypeRepo, FacilityRepo facilityRepo) {
        super(caseStatusTypeRepo, caseTypeRepo, caseRepo, partyTypeRepo, partyRepo, caseRoleTypeRepo, caseRoleRepo, contactMechanismTypeRepo, partyRoleTypeRepo, partyRoleRepo, communicationEventStatusTypeRepo, communicationEventTypeRepo, partyRelationshipTypeRepo, partyRelationshipStatusTypeRepo, priorityTypeRepo, partyRelationshipRepo, communicationEventRepo, facilityRepo, facilityTypeRepo, facilityRoleTypeRepo, facilityRoleRepo, facilityContactMechanismRepo, contactMechanismRepo, geographicBoundaryRepo, geographicBoundaryTypeRepo, contactMechanismGeographicBoundaryRepo, partyContactMechanismRepo, partyContactMechanismPurposeRepo, partyContactMechanismPurposeTypeRepo, communicationEventPurposeTypeRepo, communicationEventRoleTypeRepo);
    }

    @BeforeAll
    public static void setupWorld() {
        postgresqlDbContainer.start();
    }

    @AfterAll
    public static void tearDownWorld() {
        postgresqlDbContainer.stop();
    }

    @Before
    public void setupTheScenario() {
        theDatabaseIsEmpty();
    }

    @Given("the following types:")
    public void the_following_types(io.cucumber.datatable.DataTable dataTable) {
        final List<List<String>> dataTableLists = dataTable.asLists();
        dataTableLists
                .forEach(row -> {
                    switch (row.get(0)) {
                        case "case" -> caseTypeRepo.save(CaseType.builder()
                                .description(row.get(1))
                                .build());
                        case "case status" -> caseStatusTypeRepo.save(CaseStatusType.builder()
                                .description(row.get(1))
                                .build());
                        default -> fail("Unknown type: " + row);

                    }
                });
    }

    private void theDatabaseIsEmpty() {
        communicationEventRepo.deleteAll();
        partyRelationshipRepo.deleteAll();
        caseRoleRepo.deleteAll();
        partyRoleRepo.deleteAll();
        facilityRoleRepo.deleteAll();
        facilityContactMechanismRepo.deleteAll();
        partyContactMechanismRepo.deleteAll();
        contactMechanismGeographicBoundaryRepo.deleteAll();
        contactMechanismRepo.deleteAll();
        geographicBoundaryRepo.deleteAll();


        partyRepo.deleteAll();
        caseRepo.deleteAll();
        communicationEventRepo.deleteAll();
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
        communicationEventStatusTypeRepo.findCommunicationEventStatusTypeByParentId(root.getParent().getId(), Pageable.unpaged()).stream()
                .forEach(this::deleteCommunicationEventStatusTypeChildren);
        communicationEventStatusTypeRepo.delete(root);
    }

    private void deletePartyRoleTypeChildren(PartyRoleType root) {
        partyRoleTypeRepo.findPartyRoleTypesByParentId(root.getId(), Pageable.unpaged()).stream()
                .forEach(this::deletePartyRoleTypeChildren);
        partyRoleTypeRepo.delete(root);
    }

}
