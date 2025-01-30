package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CommunicationEvent;

import java.util.UUID;

import static org.erpmicroservices.peopleandorganizations.builders.DateTimeTestDataBuilder.zonedDateTimeNow;

public class CommunicationEventTestDataBuilder {

	public static CommunicationEvent.CommunicationEventBuilder completeCommunicationEvent() {
		return CommunicationEvent.builder()
				       .note("org.erpmicroservices.peopleandorganizations.builders.CommunicationEventTestDataBuilder " + UUID.randomUUID())
				       .started(zonedDateTimeNow());
	}

}
