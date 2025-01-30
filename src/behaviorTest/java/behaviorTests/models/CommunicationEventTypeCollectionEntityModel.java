package behaviorTests.models;

import org.springframework.core.ResolvableType;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;

public class CommunicationEventTypeCollectionEntityModel extends CollectionModel<CommunicationEventTypeEntityModel> {
    public CommunicationEventTypeCollectionEntityModel() {
    }

    public CommunicationEventTypeCollectionEntityModel(Iterable<CommunicationEventTypeEntityModel> content) {
        super(content);
    }

    public CommunicationEventTypeCollectionEntityModel(Iterable<CommunicationEventTypeEntityModel> content, Iterable<Link> links, ResolvableType fallbackType) {
        super(content, links, fallbackType);
    }

}
