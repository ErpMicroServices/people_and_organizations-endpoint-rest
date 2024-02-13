package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseRoleType;

import java.util.UUID;

public class CaseRoleTypeTestDataBuilder {

	public static CaseRoleType.CaseRoleTypeBuilder completeCaseRoleType() {
		return CaseRoleType.builder()
				       .description("org.erpmicroservices.peopleandorganizations.builders.CaseRoleTypeTestDataBuilder" + UUID.randomUUID());
	}
}
