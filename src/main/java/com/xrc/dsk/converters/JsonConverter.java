package com.xrc.dsk.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonConverter {
    private final ObjectMapper objectMapper;

    public JsonConverter() {
        objectMapper = new ObjectMapper();
    }

    public <T> String toJson(T obj) {
        try {
            String json = objectMapper.writeValueAsString(obj);
            log.info("object: {} was successfully converted to json: {}", obj, json);
            return json;
        } catch (JsonProcessingException e) {
            log.error("the issue was occurred while converting object {} to json", obj, e);
            throw new RuntimeException(e);
        }
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            if (json == null || json.isEmpty()) {
                return null;
            }
            T obj = objectMapper.readValue(json, clazz);
            log.info("object: {} was successfully converted from json: {}", obj, json);
            return obj;
        } catch (JsonProcessingException e) {
            log.error("the issue was occurred while converting object from json {}", json, e);
            throw new RuntimeException(e);
        }
    }

    public <E, F> Map<E, List<F>> fromJson(String json, Class<E> keyClass, Class<F> valueClass) {
        try {
            if (json == null || json.isEmpty()) {
                return null;
            }

            JavaType keyType = objectMapper.getTypeFactory().constructType(keyClass);
            JavaType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, valueClass);
            JavaType mapType = objectMapper.getTypeFactory().constructMapType(LinkedHashMap.class, keyType, listType);

            Map<E, List<F>> obj = objectMapper.readValue(json, mapType);
            log.info("object: {} was successfully converted from json: {}", obj, json);
            return obj;
        } catch (JsonProcessingException e) {
            log.error("the issue was occurred while converting object from json {}", json, e);
            throw new RuntimeException(e);
        }
    }

    public <E> List<E> listFromJson(String json, Class<E> valueClass) {
        try {
            if (json == null || json.isEmpty()) {
                return null;
            }

            JavaType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, valueClass);

            List<E> obj = objectMapper.readValue(json, listType);
            log.info("object: {} was successfully converted from json: {}", obj, json);
            return obj;
        } catch (JsonProcessingException e) {
            log.error("the issue was occurred while converting object from json {}", json, e);
            throw new RuntimeException(e);
        }
    }

}
