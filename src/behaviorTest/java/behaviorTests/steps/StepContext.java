package behaviorTests.steps;

import io.cucumber.spring.ScenarioScope;
import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseStatusType;
import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ScenarioScope
public class StepContext {
    public List<CaseType> caseTypes;
    public List<CaseStatusType> caseStatusTypes;

    public StepContext() {
        this.caseTypes = new ArrayList<>();
        this.caseStatusTypes = new ArrayList<>();
    }
}

