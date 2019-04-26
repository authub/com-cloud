package com.cloud.generator;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.cloud.generator.entity.GenConfig;
import com.cloud.generator.utils.GeneratorUtils;
import com.cloud.generator.utils.JdbcBean;
import com.cloud.generator.utils.JdbcUtil;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * 代码生成
 * </p>
 *
 * @Title GeneratorApplication.java
 * @Package com.cloud.codegen
 * @Author <a href="mailto:au_t@qq.com">au .T</a>
 * @Date 2019/3/20 17:39
 */
public class GeneratorApplication {
    //查询库里面所有表的SQL
    String sql = "select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables  where table_schema = (select database()) and table_name like conca('%', #{tableName}, '%') order by create_time desc";
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String JDBC_URL = "jdbc:mysql://127.0.0.1:3406/ks_platform?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowMultiQueries=true";
    private static String JDBC_USERNAME = "sinotn";
    private static String JDBC_PASSWORD = "123456";
//    private static String PLAN_ID = "ouinvvhbi28JyiHghv4C49QF9sMjvmto";

    public static void main(String[] args) throws SQLException, IOException {
        JdbcBean jdbcBean = new JdbcBean(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        //要生成表的list
        List<String> tableNames = new ArrayList<>();
        tableNames.add("client_details");
//        tableNames.add("tr_majors");

        //连接数据库
        JdbcUtil jdbcUtil = new JdbcUtil(jdbcBean.getJdbcDriver(),
                jdbcBean.getJdbcUrl(), jdbcBean.getJdbcUsername(), jdbcBean.getJdbcPassword());
        for (String tableName : tableNames) {
            GenConfig config = new GenConfig();
            config.setTableName(tableName);
            //查询表信息SQL
            String queryTable = "select table_name , engine, table_comment , create_time  from information_schema.tables where table_schema = (select database()) and table_name ='" + tableName + "';";
            List<Map<String, String>> table = jdbcUtil.selectByParams(queryTable, null);
            //查询表列信息SQL
            String queryColumns = "select column_name , data_type , column_comment , column_key , extra from information_schema.columns where table_name ='" + tableName + "'and table_schema = (select database()) order by ordinal_position";
            List<Map<String, String>> result = jdbcUtil.selectByParams(queryColumns, null);
            //生成代码
            GeneratorUtils.generatorCode(config, GeneratorApplication.map(table.get(0)), GeneratorApplication.listMap(result), zip);
        }
        IoUtil.close(zip);
        //将文件输出到本地
        OutputStream out = new FileOutputStream("F:/oauth_client_details.zip");
        InputStream is = new ByteArrayInputStream(outputStream.toByteArray());
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = is.read(buff)) != -1) {
            out.write(buff, 0, len);
        }
        is.close();
        out.close();
    }

    /**
     * 将Map里面的Key 转成驼峰
     *
     * @param map
     * @return java.util.Map<java.lang.String, java.lang.String>
     */
    public static Map<String, String> map(Map<String, String> map) {

        Map<String, String> ma = new HashMap<>();
        map.forEach((k, v) -> ma.put(StrUtil.toCamelCase(StrUtil.swapCase(k)), v));
        return ma;
    }

    public static List<Map<String, String>> listMap(List<Map<String, String>> list) {
        List<Map<String, String>> l = new ArrayList<>();
        list.forEach(obj -> l.add(map(obj)));
        return l;
    }


}