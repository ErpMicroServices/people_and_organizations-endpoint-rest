package behaviorTests.models;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseRole;
import org.springframework.hateoas.EntityModel;

public class CaseRoleEntityModel extends EntityModel<CaseRole> {
    public CaseRoleEntityModel() {
        super();
    }
    public CaseRoleEntityModel(CaseRole role) {
        super(role);
    }
}
