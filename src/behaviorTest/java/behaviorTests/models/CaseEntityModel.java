package behaviorTests.models;

import org.erpmicroservices.peopleandorganizations.api.rest.models.Case;
import org.springframework.hateoas.EntityModel;

public class CaseEntityModel extends EntityModel<Case> {


    public CaseEntityModel() {
        super();
    }


    public CaseEntityModel(Case caseToSave) {
        super(caseToSave);

    }

}
