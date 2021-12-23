package com.pachi.api.stock.core.jackson;

import com.fasterxml.jackson.core.type.TypeReference;

public interface JacksonSerializer<T> {
    T getObjectFromFile(String file, Class<T> objectClazz);

    T getObjectFromFile(String file, TypeReference<T> typeReference);

    String getStringObjectFromFile(String file, Class<T> objectClazz);

    String getObjectAsString(T objectToConvert);

    T getObjectFromString(String stringObject, Class<?> returnType);

    T getObjectFromString(String stringObject, TypeReference<T> typeReference);
}