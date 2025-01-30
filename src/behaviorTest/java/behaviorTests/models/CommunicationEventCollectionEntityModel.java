package behaviorTests.models;

import org.springframework.core.ResolvableType;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;

public class CommunicationEventCollectionEntityModel extends CollectionModel<CommunicationEventEntityModel> {
    /**
     * Creates an empty {@link CollectionModel} instance.
     */
    public CommunicationEventCollectionEntityModel() {
    }

    public CommunicationEventCollectionEntityModel(Iterable<CommunicationEventEntityModel> content) {
        super(content);
    }

    public CommunicationEventCollectionEntityModel(Iterable<CommunicationEventEntityModel> content, Iterable<Link> links, ResolvableType fallbackType) {
        super(content, links, fallbackType);
    }
}
