package behaviorTests.models;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseStatusType;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

public class CaseStatusEntityModel extends EntityModel<CaseStatusType> {
    /**
     * Creates an empty {@link EntityModel}.
     */
    public CaseStatusEntityModel() {
        super();
    }

    public CaseStatusEntityModel(CaseStatusType content) {
        super(content);
    }

    /**
     * Creates a new {@link EntityModel} with the given content and {@link Link}s.
     *
     * @param content must not be {@literal null}.
     * @param links   the links to add to the {@link EntityModel}.
     */
    public CaseStatusEntityModel(CaseStatusType content, Iterable<Link> links) {
        super(content, links);
    }
}
