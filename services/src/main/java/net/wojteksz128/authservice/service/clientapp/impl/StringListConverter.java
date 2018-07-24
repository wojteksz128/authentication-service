package net.wojteksz128.authservice.service.clientapp.impl;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        return strings != null ? String.join(DELIMITER, strings) : null;
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return dbData != null ? new ArrayList<>(Arrays.asList(dbData.split(DELIMITER))) : null;
    }
}
