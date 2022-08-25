package org.parish.attendancesb.models.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalTime;
 
@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, String> {
     
    @Override
    public String convertToDatabaseColumn(LocalTime locTime) {
        return locTime == null ? null : locTime.toString();
    }
 
    @Override
    public LocalTime convertToEntityAttribute(String sqlTime) {
        return sqlTime == null ? null : LocalTime.parse(sqlTime);
    }
}