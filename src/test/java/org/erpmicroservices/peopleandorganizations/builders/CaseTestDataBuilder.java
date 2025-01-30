package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.Case;

import java.util.UUID;

import static org.erpmicroservices.peopleandorganizations.builders.DateTimeTestDataBuilder.zonedDateTimeNow;

public class CaseTestDataBuilder {

	public static Case.CaseBuilder completeCase() {
		return Case.builder()
				       .description("Complete case " + UUID.randomUUID())
				       .startedAt(zonedDateTimeNow());
	}
}
