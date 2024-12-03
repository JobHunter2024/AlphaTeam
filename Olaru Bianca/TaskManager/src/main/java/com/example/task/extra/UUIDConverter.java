package com.example.task.extra;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
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
