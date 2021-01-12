package com.course.generator.server;

import com.course.generator.util.DbUtil;
import com.course.generator.util.Field;
import com.course.generator.util.FreemarkerUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

public class ServerGenerator {

    static String MODULE = "file";

    static String toServicePath = "./server/src/main/java/com/course/server/service/";
    static String toControllerPath = "./" + MODULE + "/src/main/java/com/course/" + MODULE + "/controller/admin/";
    static String toDtoPath = "./server/src/main/java/com/course/server/dto/";
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

        //dto
        FreemarkerUtil.initConfig("dto.ftl");
        FreemarkerUtil.generator(toDtoPath + Domain + "Dto.java", map);

        //service
        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath + Domain + "Service.java", map);

        //controller
        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath + Domain + "Controller.java", map);
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
