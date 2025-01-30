package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.PartyType;

import java.util.UUID;

public class PartyTypeTestDataBuilder {

	public static PartyType.PartyTypeBuilder completePartyType() {
		return PartyType
				       .builder()
				       .description("org.erpmicroservices.peopleandorganizations.builders.PartyTypeTestDataBuilder " + UUID.randomUUID());
	}
}
