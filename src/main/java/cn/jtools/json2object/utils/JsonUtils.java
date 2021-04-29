package cn.jtools.json2object.utils;

import cn.jtools.json2object.enums.FieldType;
import cn.jtools.json2object.model.JsonTypeModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Iterator;


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
        if (jsonNode.isObject()){
            return FieldType.OBJECT;
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



    public static void generate(JsonNode jsonNode, JsonTypeModel jsonTypeModel){
        FieldType checkType = check(jsonNode);
        if (checkType == FieldType.OBJECT){
            Iterator<String> nodeList = jsonNode.fieldNames();
            while (nodeList.hasNext()){
                String nextKey = nodeList.next();
                JsonNode next = jsonNode.get(nextKey);
                if (check(next) == FieldType.ARR || check(next) == FieldType.OBJECT){
                    JsonTypeModel objectModel = new JsonTypeModel();
                    objectModel.setType(check(next));
                    objectModel.setTypeValue(jsonNode.textValue());
                    objectModel.setTypeName(nextKey);
                    generate(next, objectModel);

                    jsonTypeModel.getChildList().add(objectModel);
                }else {
                    JsonTypeModel child = new JsonTypeModel();
                    child.setType(check(next));
                    child.setTypeName(nextKey);

                    jsonTypeModel.getChildList().add(child);
                }
            }
        }
        if (checkType == FieldType.ARR){
            Iterator<JsonNode> iterator = jsonNode.iterator();
            while (iterator.hasNext()){
                JsonNode next = iterator.next();
                if (check(next) == FieldType.ARR || check(next) == FieldType.OBJECT){
                    JsonTypeModel arrModel = new JsonTypeModel();
                    arrModel.setType(check(next));
                    arrModel.setTypeValue(jsonNode.textValue());

                    generate(next, arrModel);

                    jsonTypeModel.getChildList().add(arrModel);
                }else {
                    JsonTypeModel child = new JsonTypeModel();
                    child.setType(check(next));
                    child.setTypeValue(next.textValue());

                    jsonTypeModel.getChildList().add(child);
                }
            }
        }

    }

    public static String toJsonString(Object data){
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree("{\"name\":\"中国\",\"province\":[{\"name\":\"黑龙江\",\"cities\":{\"city\":[\"哈尔滨\",\"大庆\"]}},{\"name\":\"广东\",\"cities\":{\"city\":[\"广州\",\"深圳\",\"珠海\"]}},{\"name\":\"台湾\",\"cities\":{\"city\":[\"台北\",\"高雄\"]}},{\"name\":\"新疆\",\"cities\":{\"city\":[\"乌鲁木齐\"]}}]}}");
        JsonTypeModel jsonTypeModel = new JsonTypeModel();
        generate(jsonNode,jsonTypeModel);
        System.out.println(jsonTypeModel);
    }
}
