package com.smg.framework.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

/**
 * 根据表生成JavaBean
 *
 * @author justincai
 */
public class GenerateJavaBeanUtil {

    private static final Log log = LogFactory.getLog(GenerateJavaBeanUtil.class);

    private String tableName;// 表名
    private String[] colnames; // 列名数组
    private String[] colTypes; // 列名类型数组
    private int[] colSizes; // 列名大小数组
    private boolean f_util = false; // 是否需要导入包java.util.Date 
    private boolean f_sql = false; // 是否需要导入包java.sql.*
    private String packagePath;

    public void generateJavaBean() {

        String sql = "select * from " + tableName;
        PrintWriter pw = null;

        JdbcTemplate jt = DBUtil.getJdbcTemplate();
        SqlRowSet srs = jt.queryForRowSet(sql);
        SqlRowSetMetaData metaData = srs.getMetaData(); //得到表字段
        int size = metaData.getColumnCount(); // 统计列
        colnames = new String[size];
        colTypes = new String[size];
        colSizes = new int[size];

        for (int i = 0; i < size; i++) {

            colnames[i] = metaData.getColumnName(i + 1);
            colTypes[i] = metaData.getColumnTypeName(i + 1);

            if (colTypes[i].equalsIgnoreCase("datetime")) {
                f_util = true;
            }

            if (colTypes[i].equalsIgnoreCase("image")
                    || colTypes[i].equalsIgnoreCase("text")) {
                f_sql = true;
            }
            colSizes[i] = metaData.getColumnDisplaySize(i + 1);
        }

        String content = parse(colnames, colTypes, colSizes, packagePath);

        try {

            File directory = new File("");
            String path = this.getClass().getResource("").getPath();

            log.info("Generate JavaBean path=" + path);
            log.info("src/?/"
                    + path.substring(path.lastIndexOf("/com/",
                            path.length())));

            FileWriter fw = new FileWriter(directory.getAbsolutePath()
                    + "/src/main/java/"
                    + path.substring(
                            path.lastIndexOf("/com/", path.length()),
                            path.length()) + initcap(tableName) + ".java");

            pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            pw.close();
            log.info("Generate Success!");

        } catch (IOException e) {
            log.error(e);
        }

    }

    /**
     * 根据列，类型，生成 JAVABEAN
     *
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @param packagePath
     * @return
     */
    private String parse(String[] colnames, String[] colTypes, int[] colSizes,
            String packagePath) {

        StringBuffer sb = new StringBuffer();

        if (f_util) {
            sb.append("import java.util.Date;\r\n");
        }

        if (f_sql) {
            sb.append("import java.sql.*;\r\n");
        }

        sb.append("package ").append(packagePath).append(";");
        sb.append("\r\n\r\n");
        sb.append("/**\r\n");
        sb.append("* ").append(tableName).append(" 实体类\r\n");
        sb.append("* ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\r\n");
        sb.append("* create by GenerateJavaBeanUtil " + "\r\n");
        sb.append("*/ \r");
        sb.append("public class ").append(initcap(tableName)).append("{\r\n");
        processAllAttrs(sb);// 属性  
        processAllMethod(sb);// get set方法  
        sb.append("}\r\n");
        return sb.toString();

    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    private String initcap(String str) {

        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);

    }

    /**
     * 生成属性
     *
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tprivate ").append(sqlType2JavaType(colTypes[i])).append(" ").append(colnames[i]).append(";\r\n");
        }
    }

    /**
     * 数据库类型转换为java类型
     *
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType) {

        if (sqlType.equalsIgnoreCase("bit")) {
            return "boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "int";
        } else if (sqlType.equalsIgnoreCase("bigint")
                || sqlType.equalsIgnoreCase("timestamp")) {
            return "long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("decimal")
                || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real")
                || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("varchar")
                || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar")
                || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "byte";
        }
        return null;

    }

    /**
     * 生成方法类
     *
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic void set").append(initcap(colnames[i])).append("(").append(sqlType2JavaType(colTypes[i])).append(" ").append(colnames[i]).append("){\r\n");
            sb.append("\t\tthis.").append(colnames[i]).append("=").append(colnames[i]).append(";\r\n");
            sb.append("\t}\r\n");
            sb.append("\tpublic ").append(sqlType2JavaType(colTypes[i])).append(" get").append(initcap(colnames[i])).append("(){\r\n");
            sb.append("\t\treturn ").append(colnames[i]).append(";\r\n");
            sb.append("\t}\r\n");
        }

    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }
    
    public static void main(String[] args) {  
        GenerateJavaBeanUtil generate =new GenerateJavaBeanUtil();   
        generate.setPackagePath("com.smg.test");  
        generate.setTableName("actionsetting");  
        generate.generateJavaBean();  
    }  
}
