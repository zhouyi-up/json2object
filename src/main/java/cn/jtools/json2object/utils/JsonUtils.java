package cn.jtools.json2object.utils;

import cn.jtools.json2object.enums.FieldType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;


/**
 * @author liujun
 */
public final class JsonUtils {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public static FieldType check(JsonNode jsonNode){
        if (jsonNode.isArray()){
            return FieldType.ARR;
        }
        if (jsonNode.isBigDecimal()){
            return FieldType.BIGDECIMAL;
        }
        if (jsonNode.isBigInteger()){
            return FieldType.BIGINTEGER;
        }
        if (jsonNode.isBinary()){
            return FieldType.BYTE;
        }
        if (jsonNode.isBoolean()){
            return FieldType.BOOLEAN;
        }
        if (jsonNode.isContainerNode()){

        }
        if (jsonNode.isDouble()){
            return FieldType.DOUBLE;
        }
        if (jsonNode.isFloat()){
            return FieldType.FLOAT;
        }
        if (jsonNode.isInt()){
            return FieldType.INT;
        }
        if (jsonNode.isLong()){
            return FieldType.LONG;
        }
        if (jsonNode.isShort()){
            return FieldType.SHORT;
        }
        return FieldType.STRING;
    }

    public static String checkJson(String jsonString){
        try {
            objectMapper.readTree(jsonString);
            return null;
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }
}
