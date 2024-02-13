package behaviorTests;

import io.cucumber.spring.CucumberContextConfiguration;
import org.erpmicroservices.peopleandorganizations.api.rest.PeopleAndOrganizationsApiRestApplication;
import org.erpmicroservices.peopleandorganizations.api.rest.repositories.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@CucumberContextConfiguration
@ContextConfiguration(classes = PeopleAndOrganizationsApiRestApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringBootContext {
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

    public CucumberSpringBootContext(CaseStatusTypeRepo caseStatusTypeRepo, CaseTypeRepo caseTypeRepo, CaseRepo caseRepo, PartyTypeRepo partyTypeRepo, PartyRepo partyRepo, CaseRoleTypeRepo caseRoleTypeRepo, CaseRoleRepo caseRoleRepo, ContactMechanismTypeRepo contactMechanismTypeRepo, PartyRoleTypeRepo partyRoleTypeRepo, PartyRoleRepo partyRoleRepo, CommunicationEventStatusTypeRepo communicationEventStatusTypeRepo, CommunicationEventTypeRepo communicationEventTypeRepo, PartyRelationshipTypeRepo partyRelationshipTypeRepo, PartyRelationshipStatusTypeRepo partyRelationshipStatusTypeRepo, PriorityTypeRepo priorityTypeRepo, PartyRelationshipRepo partyRelationshipRepo, CommunicationEventRepo communicationEventRepo, FacilityRepo facilityRepo, FacilityTypeRepo facilityTypeRepo, FacilityRoleTypeRepo facilityRoleTypeRepo, FacilityRoleRepo facilityRoleRepo, FacilityContactMechanismRepo facilityContactMechanismRepo, ContactMechanismRepo contactMechanismRepo, GeographicBoundaryRepo geographicBoundaryRepo, GeographicBoundaryTypeRepo geographicBoundaryTypeRepo, ContactMechanismGeographicBoundaryRepo contactMechanismGeographicBoundaryRepo, PartyContactMechanismRepo partyContactMechanismRepo, PartyContactMechanismPurposeRepo partyContactMechanismPurposeRepo, PartyContactMechanismPurposeTypeRepo partyContactMechanismPurposeTypeRepo, CommunicationEventPurposeTypeRepo communicationEventPurposeTypeRepo, CommunicationEventRoleTypeRepo communicationEventRoleTypeRepo) {
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

    private final static DockerImageName DATABASE_IMAGE_NAME = DockerImageName
            .parse("erpmicroservices/people_and_organizations-database:latest")
            .asCompatibleSubstituteFor("postgres");
    protected static PostgreSQLContainer<?> postgresqlDbContainer = new PostgreSQLContainer<>(
            DATABASE_IMAGE_NAME
    );

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlDbContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlDbContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlDbContainer::getPassword);
    }
}
