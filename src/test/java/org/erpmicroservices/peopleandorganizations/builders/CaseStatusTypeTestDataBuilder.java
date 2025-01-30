package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.CaseStatusType;

import java.util.UUID;

public class CaseStatusTypeTestDataBuilder {
	public static CaseStatusType.CaseStatusTypeBuilder completeCaseStatusType() {
		return CaseStatusType.builder()
				       .description("org.erpmicroservices.peopleandorganizations.builders.CaseStatusTypeTestDataBuilder " + UUID.randomUUID());
	}
}
