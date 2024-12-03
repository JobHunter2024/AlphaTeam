package com.example.adminservlet.core.extra;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.UUID;

@Converter(autoApply = true)
public class UUIDConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        return uuid.toString();
    }

    @Override
    public UUID convertToEntityAttribute(String uuid) {
        if (uuid == null || uuid.isEmpty()) {
            return null;
        }
        return UUID.fromString(uuid);
    }
}
