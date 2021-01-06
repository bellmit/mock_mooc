package com.course.generator.util;

import com.course.generator.vue.EnumGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbUtil {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/course";
            String user = "root";
            String pwd = "password";
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static List<Field> getColumnByTableName(String tableName) throws Exception {
        List<Field> fieldList = new ArrayList<>();
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("show full columns from `" + tableName + "`");
        if (rs != null) {
            while (rs.next()) {
                String columnName = rs.getString("Field");
                String type = rs.getString("Type");
                String nullable = rs.getString("Null");
                String comment = rs.getString("Comment");
                Field field = new Field();
                field.setName(columnName);
                field.setNameHump(lineToHump(columnName));
                field.setNameBigHump(lineToBigHump(columnName));
                field.setType(type);
                field.setJavaType(DbUtil.sqlTypeToJavaType(type));

                field.setNullable("YES".equals(nullable));
                if (type.toUpperCase().contains("varchar".toUpperCase())) {
                    String lengthStr = type.substring(type.indexOf("(") + 1, type.length() - 1);
                    field.setLength(Integer.valueOf(lengthStr));
                } else {
                    field.setLength(0);
                }

                if (comment.contains("enum")) {
                    field.setEnum(true);

                    //e.g. get COURSE_LEVEL from enum|[CourseLevelEnum]
                    int start = comment.indexOf("[");
                    int end = comment.indexOf("]");
                    String enumName = comment.substring(start + 1, end);
                    String enumConst = EnumGenerator.toUnderLine(enumName);
                    field.setEnumConst(enumConst);
                    System.out.println(field);
                }

                fieldList.add(field);
            }
        }
        rs.close();
        stmt.close();
        conn.close();
        return fieldList;
    }

    /**
     * convert line to hump
     *
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        Pattern linePattern = Pattern.compile("_(\\w)");
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * convert to line to big hump
     *
     * @param str
     * @return
     */
    public static String lineToBigHump(String str) {
        String s = lineToHump(str);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String sqlTypeToJavaType(String sqlType) {
        if (sqlType.toUpperCase().contains("varchar".toUpperCase())
                || sqlType.toUpperCase().contains("char".toUpperCase())
                || sqlType.toUpperCase().contains("text".toUpperCase())) {
            return "String";
        } else if (sqlType.toUpperCase().contains("datetime".toUpperCase())) {
            return "Date";
        } else if (sqlType.toUpperCase().contains("int".toUpperCase())) {
            return "Integer";
        } else if (sqlType.toUpperCase().contains("long".toUpperCase())) {
            return "Long";
        } else if (sqlType.toUpperCase().contains("decimal".toUpperCase())) {
            return "BigDecimal";
        }
        return "String";
    }
}
