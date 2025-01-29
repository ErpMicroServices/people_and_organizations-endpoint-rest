package org.erpmicroservices.peopleandorganizations.api.rest.configurations;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
class StringToZoneDateTimeConverter implements Converter<String, ZonedDateTime> {
    @Override
    public ZonedDateTime convert(String value) {
        return ZonedDateTime.parse(value);
    }

}
