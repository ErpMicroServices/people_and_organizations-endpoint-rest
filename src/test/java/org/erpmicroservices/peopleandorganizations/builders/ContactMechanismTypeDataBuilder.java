package org.erpmicroservices.peopleandorganizations.builders;

import org.erpmicroservices.peopleandorganizations.api.rest.models.ContactMechanismType;

import java.util.UUID;

public class ContactMechanismTypeDataBuilder {

	public static ContactMechanismType.ContactMechanismTypeBuilder completeContactMechanismType() {
		return ContactMechanismType
				       .builder()
				       .description("org.erpmicroservices.peopleandorganizations.builders.ContactMechanismTypeDataBuilder " + UUID.randomUUID());
	}
}
