package com.example.koeco_api.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Arrays;

@Slf4j
public class UtilsValidate {
    public static String validateStr(String str) {
        return (str == null ? null : str.trim());
    }

    public static void validateFields( Object object){
        Arrays.stream(object.getClass().getDeclaredFields())
                .forEach(it ->validateField(it, object));
    }

    private static void validateField(Field field, Object object) {
        //validate filed of class
        try {
            field.setAccessible(true);

            Object value = field.get(object);

            if (value instanceof String) {
                value = validateStr((String) value);
                field.set(object, value);
            }
        } catch (Exception e) {
            log.error("err when validate field: ", e.getMessage());
        }
    }
}
