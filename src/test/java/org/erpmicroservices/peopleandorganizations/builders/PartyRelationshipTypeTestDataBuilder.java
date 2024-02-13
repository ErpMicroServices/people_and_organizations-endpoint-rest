package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.PartyRelationshipType;

import java.util.UUID;

public class PartyRelationshipTypeTestDataBuilder {

	public static PartyRelationshipType.PartyRelationshipTypeBuilder completePartyRelationshipType() {
		return PartyRelationshipType.builder()
				       .description("org.erpmicroservices.peopleandorganizations.builders.PartyRelationshipTypeTestDataBuilder " + UUID.randomUUID());
	}
}
