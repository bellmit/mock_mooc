package com.course.generator.vue;

import com.course.generator.util.FreemarkerUtil;
import com.course.server.enums.SectionChargeEnum;

import java.util.HashMap;
import java.util.Map;

public class EnumGenerator {

    static String toEnumPath = "/home/luoyumo/Desktop/mooc_frontend/admin/public/static/js/enums.js";

    public static void main(String[] args) throws Exception{
        String objectName = "SECTION_CHARGE";
        Object[] objects = SectionChargeEnum.class.getEnumConstants();

        Map<String, Object> map = new HashMap<>();
        map.put("objectName", objectName);
        map.put("objects", objects);

        FreemarkerUtil.initConfig("enums.ftl");
        FreemarkerUtil.generator(toEnumPath, map);
    }
}
