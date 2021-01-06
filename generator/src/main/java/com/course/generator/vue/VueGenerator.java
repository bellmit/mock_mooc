package com.course.generator.vue;

import com.course.generator.util.DbUtil;
import com.course.generator.util.Field;
import com.course.generator.util.FreemarkerUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

public class VueGenerator {
    static String MODULE = "business";

    static String toVuePath = "/home/luoyumo/Desktop/mooc_frontend/admin/src/views/admin/";

    static String generatorConfigPath = "./server/src/main/resources/generator/generatorConfig.xml";

    public static void main(String[] args) throws Exception {
        String module = MODULE;

        // generate the first <table> node
        File file = new File(generatorConfigPath);
        SAXReader reader = new SAXReader();
        Document doc = reader.read(file);

        // get root node
        Element rootElement = doc.getRootElement();
        // read context node
        Element contextElement = rootElement.element("context");
        Element tableElement;
        // read the first <table> node
        tableElement = contextElement.elementIterator("table").next();
        String Domain = tableElement.attributeValue("domainObjectName");
        String tableName = tableElement.attributeValue("tableName");
        String domain = Domain.substring(0,1).toLowerCase()+Domain.substring(1);

        List<Field> fieldList = DbUtil.getColumnByTableName(domain);
        Set<String> typeSet = getJavaTypes(fieldList);
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("tableName", tableName);
        map.put("module", module);
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);
        System.out.println(map.get("fieldList"));

        //vue
        FreemarkerUtil.initConfig("vue.ftl");
        FreemarkerUtil.generator(toVuePath + domain + ".vue", map);
    }

    private static Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }
}
