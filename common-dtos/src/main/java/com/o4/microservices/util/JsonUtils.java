package com.o4.microservices.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface JsonUtils {

    ObjectMapper mapper = new ObjectMapper();

    static <T> T convert(Object object, Class<T> clazz) {
        return mapper.convertValue(object, clazz);
    }
}
