package com.course.server.enums;

public enum CourseChargeEnum {

    CHARGE("C", "chargeable"),
    FREE("F", "free");

    private String code;
    private String desc;

    CourseChargeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
