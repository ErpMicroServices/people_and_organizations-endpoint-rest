package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.PartyRelationshipStatusType;

import java.util.UUID;

public class PartyRelationshipStatusTypeTestDataBuilder {

	public static PartyRelationshipStatusType.PartyRelationshipStatusTypeBuilder completePartyRelationshipStatusType() {
		return PartyRelationshipStatusType.builder()
				       .description("org.erpmicroservices.peopleandorganizations.builders.PartyRelationshipStatusTypeTestDataBuilder " + UUID.randomUUID());
	}
}
