package cn.jtools.json2object.enums;

/**
 * @author liujun
 */
public enum FieldType {
    /**
     * int
     */
    INT("int"),
    LONG("long"),
    DOUBLE("double"),
    ARR("List"),
    BIGDECIMAL("BigDecimal"),
    BIGINTEGER("BigInteger"),
    BYTE("byte"),
    BOOLEAN("boolean"),
    FLOAT("float"),
    SHORT("short"),
    STRING("String")


    ;
    private final String typeName;

    FieldType(String typeName){
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
