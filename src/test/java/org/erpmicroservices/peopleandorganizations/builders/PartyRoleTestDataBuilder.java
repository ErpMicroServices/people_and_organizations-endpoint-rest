package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.PartyRole;

import java.time.LocalDate;

public class PartyRoleTestDataBuilder {

	public static PartyRole.PartyRoleBuilder completePartyRole() {
		return PartyRole.builder()
				       .fromDate(LocalDate.now());
	}
}
