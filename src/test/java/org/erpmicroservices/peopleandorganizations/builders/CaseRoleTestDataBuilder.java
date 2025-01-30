package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseRole;

import java.time.LocalDate;

public class CaseRoleTestDataBuilder {

	public static CaseRole.CaseRoleBuilder completeCaseRole() {
		return CaseRole.builder()
				       .fromDate(LocalDate.now());
	}
}
