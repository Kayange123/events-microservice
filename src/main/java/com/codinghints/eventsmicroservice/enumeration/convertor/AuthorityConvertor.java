package com.codinghints.eventsmicroservice.enumeration.convertor;

import com.codinghints.eventsmicroservice.enumeration.Authority;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class AuthorityConvertor implements AttributeConverter<Authority, String> {

    @Override
    public String convertToDatabaseColumn(Authority authority) {
        if(authority == null){
            return null;
        }
        return authority.getValue();
    }

    @Override
    public Authority convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }
        return Stream.of(Authority.values())
                .filter(value->value.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);

    }
}
