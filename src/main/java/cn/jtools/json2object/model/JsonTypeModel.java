package cn.jtools.json2object.model;

import cn.jtools.json2object.enums.FieldType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujun
 */
public class JsonTypeModel {

    private String typeName;
    private String typeValue;
    private FieldType type;
    private List<JsonTypeModel> childList = new ArrayList<>();

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public List<JsonTypeModel> getChildList() {
        return childList;
    }

    public void setChildList(List<JsonTypeModel> childList) {
        this.childList = childList;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }
}
