package behaviorTests.clients;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEvent;
import org.springframework.hateoas.EntityModel;

public class CommunicationEventEntityModel extends EntityModel<CommunicationEvent> {
    public CommunicationEventEntityModel() {}

    public CommunicationEventEntityModel(CommunicationEvent content) {
        super(content);
    }
}
