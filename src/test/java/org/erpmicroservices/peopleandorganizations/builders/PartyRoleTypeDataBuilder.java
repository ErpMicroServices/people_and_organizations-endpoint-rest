package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.PartyRoleType;

import java.util.UUID;

public class PartyRoleTypeDataBuilder {

	public static PartyRoleType.PartyRoleTypeBuilder completePartyRoleType() {
		return PartyRoleType.builder()
				       .description("org.erpmicroservices.peopleandorganizations.builders.PartyRoleTypeDataBuilder " + UUID.randomUUID());
	}
}
