package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.PriorityType;

import java.util.UUID;

public class PriorityTypeTestDataBuilder {
	public static PriorityType.PriorityTypeBuilder completePriorityType() {
		return PriorityType.builder()
				       .description("org.erpmicroservices.peopleandorganizations.builders.PriorityTypeTestDataBuilder " + UUID.randomUUID());
	}
}
