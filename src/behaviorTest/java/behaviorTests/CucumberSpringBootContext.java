package behaviorTests;

import behaviorTests.steps.StepContext;
import io.cucumber.spring.CucumberContextConfiguration;
import org.erpmicroservices.peopleandorganizations.api.rest.PeopleAndOrganizationsApiRestApplication;
import org.erpmicroservices.peopleandorganizations.api.rest.repositories.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@CucumberContextConfiguration
@ContextConfiguration(classes = PeopleAndOrganizationsApiRestApplication.class)
@SpringBootTest(classes = TestRestTemplateConfig.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = {
        "spring.profiles.active=development"
})
//@Testcontainers
public class CucumberSpringBootContext {

//    @Container
//    @ServiceConnection
//    protected static final PostgreSQLContainer<?> postgreSQLContainer =
//            new PostgreSQLContainer<>(
//                    DockerImageName.parse("erpmicroservices/people_and_organizations-database:latest")
//                            .asCompatibleSubstituteFor("postgres"));

    protected final CaseStatusTypeRepo caseStatusTypeRepo;
    protected final CaseTypeRepo caseTypeRepo;
    protected final CaseRepo caseRepo;
    protected final PartyTypeRepo partyTypeRepo;
    protected final PartyRepo partyRepo;
    protected final CaseRoleTypeRepo caseRoleTypeRepo;
    protected final CaseRoleRepo caseRoleRepo;
    protected final ContactMechanismTypeRepo contactMechanismTypeRepo;
    protected final PartyRoleTypeRepo partyRoleTypeRepo;
    protected final PartyRoleRepo partyRoleRepo;
    protected final CommunicationEventStatusTypeRepo communicationEventStatusTypeRepo;
    protected final CommunicationEventTypeRepo communicationEventTypeRepo;
    protected final PartyRelationshipTypeRepo partyRelationshipTypeRepo;
    protected final PartyRelationshipStatusTypeRepo partyRelationshipStatusTypeRepo;
    protected final PriorityTypeRepo priorityTypeRepo;
    protected final PartyRelationshipRepo partyRelationshipRepo;
    protected final CommunicationEventRepo communicationEventRepo;
    protected final FacilityRepo facilityRepo;
    protected final FacilityTypeRepo facilityTypeRepo;
    protected final FacilityRoleTypeRepo facilityRoleTypeRepo;
    protected final FacilityRoleRepo facilityRoleRepo;
    protected final FacilityContactMechanismRepo facilityContactMechanismRepo;
    protected final ContactMechanismRepo contactMechanismRepo;
    protected final GeographicBoundaryRepo geographicBoundaryRepo;
    protected final GeographicBoundaryTypeRepo geographicBoundaryTypeRepo;
    protected final ContactMechanismGeographicBoundaryRepo contactMechanismGeographicBoundaryRepo;
    protected final PartyContactMechanismRepo partyContactMechanismRepo;
    protected final PartyContactMechanismPurposeRepo partyContactMechanismPurposeRepo;
    protected final PartyContactMechanismPurposeTypeRepo partyContactMechanismPurposeTypeRepo;
    protected final CommunicationEventPurposeTypeRepo communicationEventPurposeTypeRepo;
    protected final CommunicationEventRoleTypeRepo communicationEventRoleTypeRepo;
    protected final RestTemplate template;
    protected final StepContext stepContext;

    public CucumberSpringBootContext(StepContext stepContext, RestTemplate template, CaseStatusTypeRepo caseStatusTypeRepo, CaseTypeRepo caseTypeRepo, CaseRepo caseRepo, PartyTypeRepo partyTypeRepo, PartyRepo partyRepo, CaseRoleTypeRepo caseRoleTypeRepo, CaseRoleRepo caseRoleRepo, ContactMechanismTypeRepo contactMechanismTypeRepo, PartyRoleTypeRepo partyRoleTypeRepo, PartyRoleRepo partyRoleRepo, CommunicationEventStatusTypeRepo communicationEventStatusTypeRepo, CommunicationEventTypeRepo communicationEventTypeRepo, PartyRelationshipTypeRepo partyRelationshipTypeRepo, PartyRelationshipStatusTypeRepo partyRelationshipStatusTypeRepo, PriorityTypeRepo priorityTypeRepo, PartyRelationshipRepo partyRelationshipRepo, CommunicationEventRepo communicationEventRepo, FacilityRepo facilityRepo, FacilityTypeRepo facilityTypeRepo, FacilityRoleTypeRepo facilityRoleTypeRepo, FacilityRoleRepo facilityRoleRepo, FacilityContactMechanismRepo facilityContactMechanismRepo, ContactMechanismRepo contactMechanismRepo, GeographicBoundaryRepo geographicBoundaryRepo, GeographicBoundaryTypeRepo geographicBoundaryTypeRepo, ContactMechanismGeographicBoundaryRepo contactMechanismGeographicBoundaryRepo, PartyContactMechanismRepo partyContactMechanismRepo, PartyContactMechanismPurposeRepo partyContactMechanismPurposeRepo, PartyContactMechanismPurposeTypeRepo partyContactMechanismPurposeTypeRepo, CommunicationEventPurposeTypeRepo communicationEventPurposeTypeRepo, CommunicationEventRoleTypeRepo communicationEventRoleTypeRepo) {
        this.stepContext = stepContext;
        this.template = template;
        this.caseStatusTypeRepo = caseStatusTypeRepo;
        this.caseTypeRepo = caseTypeRepo;
        this.caseRepo = caseRepo;
        this.partyTypeRepo = partyTypeRepo;
        this.partyRepo = partyRepo;
        this.caseRoleTypeRepo = caseRoleTypeRepo;
        this.caseRoleRepo = caseRoleRepo;
        this.contactMechanismTypeRepo = contactMechanismTypeRepo;
        this.partyRoleTypeRepo = partyRoleTypeRepo;
        this.partyRoleRepo = partyRoleRepo;
        this.communicationEventStatusTypeRepo = communicationEventStatusTypeRepo;
        this.communicationEventTypeRepo = communicationEventTypeRepo;
        this.partyRelationshipTypeRepo = partyRelationshipTypeRepo;
        this.partyRelationshipStatusTypeRepo = partyRelationshipStatusTypeRepo;
        this.priorityTypeRepo = priorityTypeRepo;
        this.partyRelationshipRepo = partyRelationshipRepo;
        this.communicationEventRepo = communicationEventRepo;
        this.facilityRepo = facilityRepo;
        this.facilityTypeRepo = facilityTypeRepo;
        this.facilityRoleTypeRepo = facilityRoleTypeRepo;
        this.facilityRoleRepo = facilityRoleRepo;
        this.facilityContactMechanismRepo = facilityContactMechanismRepo;
        this.contactMechanismRepo = contactMechanismRepo;
        this.geographicBoundaryRepo = geographicBoundaryRepo;
        this.geographicBoundaryTypeRepo = geographicBoundaryTypeRepo;
        this.contactMechanismGeographicBoundaryRepo = contactMechanismGeographicBoundaryRepo;
        this.partyContactMechanismRepo = partyContactMechanismRepo;
        this.partyContactMechanismPurposeRepo = partyContactMechanismPurposeRepo;
        this.partyContactMechanismPurposeTypeRepo = partyContactMechanismPurposeTypeRepo;
        this.communicationEventPurposeTypeRepo = communicationEventPurposeTypeRepo;
        this.communicationEventRoleTypeRepo = communicationEventRoleTypeRepo;

    }
}
