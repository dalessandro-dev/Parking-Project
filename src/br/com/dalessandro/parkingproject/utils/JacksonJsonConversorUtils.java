package br.com.dalessandro.parkingproject.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJsonConversorUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static <T> T convertJsonToObject(String json, Class<T> targetClass) throws JsonProcessingException {
        return mapper.readValue(json, targetClass);
    }
}
