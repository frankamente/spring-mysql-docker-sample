package com.pachi.api.stock.core.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class JacksonSerializerImpl<T> implements JacksonSerializer<T> {
    private final ObjectMapper mapper = (new JacksonConfigurationBuilderImpl()).buildObjectMapper();

    public JacksonSerializerImpl() {
    }

    public T getObjectFromFile(final String file, final Class<T> objectClazz) {
        try {
            return this.mapper.readValue(this.getClass().getResourceAsStream(file), objectClazz);
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public T getObjectFromFile(final String file, TypeReference<T> typeReference) {
        try {
            return this.mapper.readValue(this.getClass().getResourceAsStream(file), typeReference);
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public String getStringObjectFromFile(String file, Class<T> objectClazz) {
        try {
            return this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.mapper.readValue(this.getClass().getResourceAsStream(file), objectClazz));
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public String getObjectAsString(T objectToConvert) {
        try {
            return this.mapper.writeValueAsString(objectToConvert);
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    public T getObjectFromString(final String stringObject, final Class<?> returnType) {
        try {
            return (T) this.mapper.readValue(stringObject, returnType);
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public T getObjectFromString(final String stringObject, final TypeReference<T> typeReference) {
        try {
            return this.mapper.readValue(stringObject, typeReference);
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }
}
