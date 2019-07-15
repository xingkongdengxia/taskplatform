package com.magicube.framework.common.utils;

import com.google.common.io.Files;
import static com.magicube.framework.common.utils.StringUtil.lineToHump;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 代码生成类
 *
 * @author justincai
 */
public class MybatisGeneratorUtil {

    private static final Log log = LogFactory.getLog(MybatisGeneratorUtil.class);

    // generatorConfig模板路径
    private static String generatorConfig_vm = "/template/generatorConfig.vm";
    // Service模板路径
    private static String service_vm = "/template/Service.vm";
    // ServiceMock模板路径
    private static String serviceMock_vm = "/template/ServiceMock.vm";
    // ServiceImpl模板路径
    private static String serviceImpl_vm = "/template/ServiceImpl.vm";

    /**
     * 根据模板生成generatorConfig.xml文件
     *
     * @param jdbc_driver 驱动路径
     * @param jdbc_url 链接
     * @param jdbc_username 帐号
     * @param jdbc_password 密码
     * @param module 项目模块
     * @param database 数据库
     * @param table_prefix 表前缀
     * @param package_name 包名
     * @param last_insert_id_tables
     * @throws java.lang.Exception
     */
    public static void generator(
            String jdbc_driver,
            String jdbc_url,
            String jdbc_username,
            String jdbc_password,
            String module,
            String database,
            String table_prefix,
            String package_name,
            Map<String, String> last_insert_id_tables) throws Exception {

        generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath();
        log.debug("generatorConfig_vm:" + generatorConfig_vm);

        service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath();
        log.debug("service_vm:" + service_vm);

        serviceMock_vm = MybatisGeneratorUtil.class.getResource(serviceMock_vm).getPath();
        log.debug("serviceMock_vm:" + serviceMock_vm);

        serviceImpl_vm = MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath();
        log.debug("serviceImpl_vm:" + serviceImpl_vm);

        String targetProject = module + "/" + module + "-dao";
        log.debug("targetProject:" + targetProject);

        String basePath = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "").replace(targetProject, "");
        log.debug("basePath:" + basePath);

        //String generatorConfig_xml = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "") + "/src/main/resources/generatorConfig.xml";
        String generatorConfig_xml = basePath + module + "/generatorConfig.xml";
        log.debug("generatorConfig_xml:" + generatorConfig_xml);

        targetProject = basePath + targetProject;
        log.debug("targetProject:" + targetProject);

        String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database + "' AND table_name LIKE '" + table_prefix + "_%';";
        log.debug("sql:" + sql);

        System.out.println("========== 开始生成generatorConfig.xml文件 ==========");
        List<Map<String, Object>> tables = new ArrayList<>();

        try {

            VelocityContext context = new VelocityContext();
            Map<String, Object> table;

            // 查询定制前缀项目的所有表
            JdbcUtil jdbcUtil = new JdbcUtil(jdbc_driver, jdbc_url, jdbc_username, AESUtil.AESDecode(jdbc_password));
            List<Map> result = jdbcUtil.selectByParams(sql, null);
            for (Map map : result) {
                System.out.println(map.get("TABLE_NAME"));
                table = new HashMap<>();
                table.put("table_name", map.get("TABLE_NAME"));
                table.put("model_name", lineToHump(ObjectUtils.toString(map.get("TABLE_NAME"))));
                tables.add(table);
            }
            jdbcUtil.release();

            String targetProject_sqlMap = basePath + module + "/" + module + "-rpc-service";
            log.debug("targetProject_sqlMap:" + targetProject_sqlMap);
            context.put("tables", tables);
            context.put("generator_javaModelGenerator_targetPackage", package_name + ".dao.model");
            context.put("generator_sqlMapGenerator_targetPackage", package_name + ".dao.mapper");
            context.put("generator_javaClientGenerator_targetPackage", package_name + ".dao.mapper");
            context.put("targetProject", targetProject);
            context.put("targetProject_sqlMap", targetProject_sqlMap);
            context.put("generator_jdbc_driver", jdbc_driver);
            //jdbc_url在xml配置中必须把&转换为&amp
            String xml_jdbc_url = jdbc_url.replace("&", "&amp;");
            context.put("generator_jdbc_url", xml_jdbc_url);
            context.put("generator_jdbc_username", jdbc_username);
            context.put("generator_jdbc_password", AESUtil.AESDecode(jdbc_password));
            context.put("last_insert_id_tables", last_insert_id_tables);
            context.put("database", database);
            //生成generatorConfig_xml目录
            File generatorConfig_xml_file = new File(generatorConfig_xml);
            if (!generatorConfig_xml_file.exists()) {
                Files.createParentDirs(generatorConfig_xml_file);
            }

            VelocityUtil.generate(generatorConfig_vm, generatorConfig_xml, context);

            // 删除旧代码
            File javaModelGenerator_targetPackage_file = new File(targetProject + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/model");
            Files.createParentDirs(javaModelGenerator_targetPackage_file);
            deleteDir(javaModelGenerator_targetPackage_file);

            File sqlMapGenerator_targetPackage_file = new File(targetProject + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/mapper");
            Files.createParentDirs(sqlMapGenerator_targetPackage_file);
            deleteDir(sqlMapGenerator_targetPackage_file);

            File javaClientGenerator_targetPackage_file = new File(targetProject_sqlMap + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/mapper");
            Files.createParentDirs(javaClientGenerator_targetPackage_file);
            deleteDir(javaClientGenerator_targetPackage_file);

        } catch (Exception e) {
            log.error("根据模板生成generatorConfig.xml文件报错！", e);
        }

        System.out.println("========== 结束生成generatorConfig.xml文件 ==========");

        System.out.println("========== 开始运行MybatisGenerator ==========");
        //建目录

        List<String> warnings = new ArrayList<>();
        File configFile = new File(generatorConfig_xml);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            System.out.println(warning);
        }
        System.out.println("========== 结束运行MybatisGenerator ==========");

        System.out.println("========== 开始生成Service ==========");
        String ctime = new SimpleDateFormat("yyyy/M/d").format(new Date());
        String servicePath = basePath + module + "/" + module + "-rpc-api" + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/rpc/api";
        log.debug("servicePath:" + servicePath);
        String serviceImplPath = basePath + module + "/" + module + "-rpc-service" + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/rpc/service/impl";
        log.debug("serviceImplPath:" + serviceImplPath);

        for (int i = 0; i < tables.size(); i++) {
            String model = StringUtil.lineToHump(ObjectUtils.toString(tables.get(i).get("table_name")));
            String service = servicePath + "/" + model + "Service.java";
            String serviceMock = servicePath + "/" + model + "ServiceMock.java";
            String serviceImpl = serviceImplPath + "/" + model + "ServiceImpl.java";
            // 生成service
            File serviceFile = new File(service);
            deleteDir(serviceFile); //删除旧代码
            if (!serviceFile.exists()) {
                Files.createParentDirs(serviceFile);
                VelocityContext context = new VelocityContext();
                context.put("package_name", package_name);
                context.put("model", model);
                context.put("ctime", ctime);
                VelocityUtil.generate(service_vm, service, context);
                System.out.println(service);
            }
            // 生成serviceMock
            File serviceMockFile = new File(serviceMock);
            deleteDir(serviceMockFile); //删除旧代码
            if (!serviceMockFile.exists()) {
                Files.createParentDirs(serviceMockFile);
                VelocityContext context = new VelocityContext();
                context.put("package_name", package_name);
                context.put("model", model);
                context.put("ctime", ctime);
                VelocityUtil.generate(serviceMock_vm, serviceMock, context);
                System.out.println(serviceMock);
            }
            // 生成serviceImpl
            File serviceImplFile = new File(serviceImpl);
            deleteDir(serviceImplFile); //删除旧代码
            if (!serviceImplFile.exists()) {
                Files.createParentDirs(serviceImplFile);
                VelocityContext context = new VelocityContext();
                context.put("package_name", package_name);
                context.put("model", model);
                context.put("mapper", StringUtil.toLowerCaseFirstOne(model));
                context.put("ctime", ctime);
                VelocityUtil.generate(serviceImpl_vm, serviceImpl, context);
                System.out.println(serviceImpl);
            }
        }
        System.out.println("========== 结束生成Service ==========");

    }

    // 递归删除非空文件夹
    public static void deleteDir(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                deleteDir(file);
            }
        } else {
            dir.delete();
        }
    }

}
