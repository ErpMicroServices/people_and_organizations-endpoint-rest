package behaviorTests.models;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEventType;
import org.springframework.hateoas.EntityModel;

public class CommunicationEventTypeEntityModel extends EntityModel<CommunicationEventType> {
    public CommunicationEventTypeEntityModel() {
    }

    public CommunicationEventTypeEntityModel(CommunicationEventType type) {
        super(type);
    }
}
