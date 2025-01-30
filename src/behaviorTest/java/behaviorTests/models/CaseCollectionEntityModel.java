package behaviorTests.models;

import org.springframework.core.ResolvableType;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;

public class CaseCollectionEntityModel extends CollectionModel<CaseEntityModel> {
    /**
     * Creates an empty {@link CollectionModel} instance.
     */
    public CaseCollectionEntityModel() {
    }

    public CaseCollectionEntityModel(Iterable<CaseEntityModel> content) {
        super(content);
    }

    public CaseCollectionEntityModel(Iterable<CaseEntityModel> content, Iterable<Link> links, ResolvableType fallbackType) {
        super(content, links, fallbackType);
    }
}
