package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.Party;

import java.util.UUID;

public class PartyTestDataBuilder {

	public static Party.PartyBuilder completeParty() {
		return Party.builder()
				       .comment("org.erpmicroservices.peopleandorganizations.builders.PartyTestDataBuilder " + UUID.randomUUID());
	}
}
