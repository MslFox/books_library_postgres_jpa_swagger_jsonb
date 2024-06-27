package com.mslfox.library_postgres_jpa_swagger.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter(autoApply = true)
@RequiredArgsConstructor
public class AuthorDetailsConverter implements AttributeConverter<JsonNode, String> {
    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(JsonNode jsonNode) {
        try {
            return objectMapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JsonNode to String", e);
        }
    }

    @Override
    public JsonNode convertToEntityAttribute(String s) {
        try {
            return s == null ? null : objectMapper.readValue(s, JsonNode.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert string to JsonNode", e);
        }
    }
}
