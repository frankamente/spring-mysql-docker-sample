package com.pachi.api.stock.core.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public interface JacksonConfigurationBuilder {
    Jackson2ObjectMapperBuilder buildJackson2ObjectMapperBuilder();

    ObjectMapper buildObjectMapper();
}
