package org.parish.attendancesb.models.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, String> {

    private static final String ZONE = "UTC-5";

    @Override
    public String convertToDatabaseColumn(LocalDateTime locDateTime) {
        return locDateTime == null ? null : locDateTime.toString();
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String sqlDateTime) {
        return sqlDateTime == null ? null : LocalDateTime.parse(sqlDateTime);
    }
}