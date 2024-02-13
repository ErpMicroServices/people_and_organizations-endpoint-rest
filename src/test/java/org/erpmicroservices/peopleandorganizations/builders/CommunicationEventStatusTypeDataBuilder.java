package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEventStatusType;

import java.util.UUID;

public class CommunicationEventStatusTypeDataBuilder {

	public static CommunicationEventStatusType.CommunicationEventStatusTypeBuilder completeCommunicationEventStatusType() {
		return CommunicationEventStatusType.builder()
				       .description("org.erpmicroservices.peopleandorganizations.builders.CommunicationEventStatusTypeDataBuilder " + UUID.randomUUID());
	}
}
