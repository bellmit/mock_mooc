package com.course.generator.util;

public class Field {
    private String name; //course_id
    private String nameHump; //courseId
    private String nameBigHump; //CourseId
    private String tableName; //course
    private String type; //char(8)
    private String javaType; //String

    private Boolean nullable;
    private Integer length;

    private Boolean isEnum = false;
    private String enumConst = ""; //such as COURSE_LEVEL


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameHump() {
        return nameHump;
    }

    public void setNameHump(String nameHump) {
        this.nameHump = nameHump;
    }

    public String getNameBigHump() {
        return nameBigHump;
    }

    public void setNameBigHump(String nameBigHump) {
        this.nameBigHump = nameBigHump;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean getEnum() {
        return isEnum;
    }

    public void setEnum(Boolean anEnum) {
        this.isEnum = anEnum;
    }

    public String getEnumConst() {
        return enumConst;
    }

    public void setEnumConst(String enumConst) {
        this.enumConst = enumConst;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", nameHump='" + nameHump + '\'' +
                ", nameBigHump='" + nameBigHump + '\'' +
                ", tableName='" + tableName + '\'' +
                ", type='" + type + '\'' +
                ", javaType='" + javaType + '\'' +
                ", nullable=" + nullable +
                ", length=" + length +
                ", isEnum=" + isEnum +
                ", enumConst='" + enumConst + '\'' +
                '}'+"\n";
    }
}
