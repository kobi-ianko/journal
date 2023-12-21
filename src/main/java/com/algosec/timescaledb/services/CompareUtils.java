package com.algosec.timescaledb.services;

import com.algosec.timescaledb.entities.RuleData;
import com.algosec.timescaledb.entities.RuleDataDeserializer;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CompareUtils {
    
    public static Map<String, String> compareRuleData(RuleData older, RuleData newer) throws IllegalAccessException {

        Map<String, String> result = new HashMap<>();
        Field[] fields =  older.getClass().getDeclaredFields();
        
        for(Field field: fields) {
            field.setAccessible(true);
            String olderField = (String)field.get(older);
            String newerField = (String)field.get(newer);

            if(!Objects.equals(olderField, newerField)) {
                result.put(field.getName(), "Was: " + olderField+". Now: " + newerField);
            }
        }
        return result;
        
    }
}
