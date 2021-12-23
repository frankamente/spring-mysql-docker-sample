package com.pachi.api.stock.core.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
public class JacksonConfigurationBuilderImpl implements JacksonConfigurationBuilder {
    public JacksonConfigurationBuilderImpl() {
    }

    public Jackson2ObjectMapperBuilder buildJackson2ObjectMapperBuilder() {
        return (new Jackson2ObjectMapperBuilder()).indentOutput(false);
    }

    public ObjectMapper buildObjectMapper() {
        ObjectMapper objectMapper = this.buildJackson2ObjectMapperBuilder().build();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper;
    }
}
