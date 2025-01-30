package behaviorTests.models;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseType;
import org.springframework.hateoas.EntityModel;

public class CaseTypeEntityModel extends EntityModel<CaseType> {
    /**
     * Creates an empty {@link EntityModel}.
     */
    public CaseTypeEntityModel() {
    }

    public CaseTypeEntityModel(CaseType type) {
        super(type);
    }
}
