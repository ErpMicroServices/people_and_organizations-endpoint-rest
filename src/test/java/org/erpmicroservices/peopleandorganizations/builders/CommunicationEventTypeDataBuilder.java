package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEventType;

import java.util.UUID;

public class CommunicationEventTypeDataBuilder {

	public static CommunicationEventType.CommunicationEventTypeBuilder completeCommunicationEventType() {
		return CommunicationEventType.builder()
				       .description("org.erpmicroservices.peopleandorganizations.builders.CommunicationEventTypeDataBuilder " + UUID.randomUUID());
	}
}
