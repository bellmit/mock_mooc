package com.course.generator.vue;

import com.course.generator.util.FreemarkerUtil;
import com.course.server.enums.CourseChargeEnum;
import com.course.server.enums.CourseLevelEnum;
import com.course.server.enums.CourseStatusEnum;
import com.course.server.enums.SectionChargeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnumGenerator {

    static String toEnumPath = "/home/luoyumo/Desktop/mooc_frontend/admin/public/static/js/enums.js";
    static List<Map<String,Object[]>> objectList = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        insert(SectionChargeEnum.class);
        insert(CourseChargeEnum.class);
        insert(CourseLevelEnum.class);
        insert(CourseStatusEnum.class);
        Map<String, Object> map = new HashMap<>();
        map.put("objectList", objectList);

        FreemarkerUtil.initConfig("enums.ftl");
        FreemarkerUtil.generator(toEnumPath, map);
    }

    private static void insert(Class clazz){
        Map<String, Object[]> map = new HashMap<>();
        map.put(toUnderLine(clazz.getSimpleName()), clazz.getEnumConstants());
        objectList.add(map);
    }

    /**
     * _section_charge_enum => SECTION_CHARGE
     * @param str
     * @return
     */
    public static String toUnderLine(String str){
        String result = underline(str).toString();
        return result.substring(1, result.length()).toUpperCase().replace("_ENUM", "");
    }

    /**
     * SectionChargeEnum => _section_charge_enum
     * @param str
     * @return
     */
    private static StringBuffer underline(String str){
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if(matcher.find()){
            sb = new StringBuffer();
            matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());
            matcher.appendTail(sb);
        } else {
            return sb;
        }
        return underline(sb.toString());
    }


}
