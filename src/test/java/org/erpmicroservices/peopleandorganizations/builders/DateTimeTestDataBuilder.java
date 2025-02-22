package org.erpmicroservices.peopleandorganizations.builders;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class DateTimeTestDataBuilder {

	public static ZonedDateTime zonedDateTimeNow() {
		return ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC).withNano(0);
	}
}
