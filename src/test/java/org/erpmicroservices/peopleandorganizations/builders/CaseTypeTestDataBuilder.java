package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseType;

import java.util.UUID;

public class CaseTypeTestDataBuilder {

	public static CaseType.CaseTypeBuilder completeCaseType() {
		return CaseType.builder()
				       .description("org.erpmicroservices.peopleandorganizations.builders.CaseTypeTestDataBuilder " + UUID.randomUUID());
	}
}
