package behaviorTests.steps;

import behaviorTests.models.CaseEntityModel;
import behaviorTests.models.CaseRoleEntityModel;
import io.cucumber.spring.ScenarioScope;
import org.erpmicroservices.peopleandorganizations.api.rest.models.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@ScenarioScope
public class StepContext {

    public List<CaseType> caseTypes = new ArrayList<>();
    public List<CaseStatusType> caseStatusTypes = new ArrayList<>();
    public List<PartyType> partyTypes = new ArrayList<>();
    public List<PartyRoleType> partyRoleTypes = new ArrayList<>();
    public List<CommunicationEventType> communicationEventTypes = new ArrayList<>();
    public List<CommunicationEventRoleType> communicationEventRoleTypes = new ArrayList<>();
    public List<PartyRelationshipType> partyRelationshipTypes = new ArrayList<>();
    public List<PartyRelationshipStatusType> partyRelationshipStatusTypes = new ArrayList<>();
    public List<CommunicationEventStatusType> communicationEventStatusTypes = new ArrayList<>();
    public List<ContactMechanismType> contactMechanismTypes = new ArrayList<>();
    public List<PriorityType> priorityTypes = new ArrayList<>();
    public List<Party> parties = new ArrayList<>();
    public PartyRelationship expectedPartyRelationship;
    public Pageable pageable = Pageable.unpaged();
    public List<Case> expectedCases = new ArrayList<>();
    public Case expectedCase = new Case();
    public List<Case> actualCases = new ArrayList<>();
    public ResponseEntity<CaseEntityModel> actualResponseEntityCase;
    public ResponseEntity<Void> actualResponseEntityVoid;
    public List<CaseRoleType> caseRoleTypes = new ArrayList<>();
    public List<CommunicationEvent> expectedCommunicationEvents = new ArrayList<>();
    public ResponseEntity<CaseRoleEntityModel> actualCaseRole;
    public LocalDate expectedCaseRoleFromDate;

    public StepContext() {
    }

}

