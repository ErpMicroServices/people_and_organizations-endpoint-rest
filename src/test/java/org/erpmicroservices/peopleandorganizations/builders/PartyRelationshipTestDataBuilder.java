package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.PartyRelationship;

import java.time.LocalDate;
import java.util.UUID;

public class PartyRelationshipTestDataBuilder {

	public static PartyRelationship.PartyRelationshipBuilder completePartyRelationship() {
		return PartyRelationship.builder()
				       .comment("org.erpmicroservices.peopleandorganizations.builders.PartyRelationshipTestDataBuilder " + UUID.randomUUID())
				       .fromDate(LocalDate.now());
	}
}
