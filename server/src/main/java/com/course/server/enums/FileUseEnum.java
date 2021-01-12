package com.course.server.enums;

public enum FileUseEnum {

    COURSE("C", "course"),
    TEACHER("T", "teacher");

    private String code;
    private String desc;

    FileUseEnum(String code, String desc) {
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

    /**
     *
     * @param code
     * @return e.g. COURSE("C", "course"),
     */
   public static FileUseEnum getByCode(String code){
        for(FileUseEnum e: FileUseEnum.values()){
            if(e.getCode().equals(code)){
                return e;
            }
        }
        return null;
   }
}
