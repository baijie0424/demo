package com.cetc15s.utils;

import com.cetc15s.pojo.Student;

import javax.sql.DataSource;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName CreateSqlUtil
 * @Description
 * @Author bj
 * @Date 2020/7/9 0:54
 * @Version 1.0
 */
public class CreateSqlUtil {

    private static DataSource dataSource;

    public static String sqlSelect = "";

//    public static String selectQuerySQL(Map<String, Object> map, String tableName) {
//        if (map.isEmpty() || map.size() == 0) {
//            sqlSelect = "select*from" + tableName;
//        } else {
//            sqlSelect = "select*from" + tableName + "where";
//            Set set = map.keySet();
//            Iterator iterator = set.iterator();
//            int index = 0;
//            while (iterator.hasNext()) {
//                String key = (String) iterator.next();
//                Object value = map.get(key);
//                if (value instanceof String) {
//                    value = "'" + value + "'";
//                }
//                index++;
//                if ((index == 1 && map.size() == 1) || map.size() == index) {
//                    sqlSelect += key + "=" + value + " ";
//                } else {
//                    sqlSelect += key + "=" + value + " " + "and" + " ";
//                }
//            }
//        }
//        System.out.println("待执行的select SQL："+sqlSelect);
//        return sqlSelect;
//    }

    public static String insertSQL(Map<String, Object> map) {
        Iterator<String> iterator = map.keySet().iterator();
        StringBuilder sqlTable = new StringBuilder("insert into");
        StringBuilder sqlKey = new StringBuilder("(");
        StringBuilder sqlVaule = new StringBuilder(" values(");
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (map.get(key).toString() == null || map.get(key).toString().length() == 0) {
                iterator.remove();
            } else {
                if (key.equals("tableName")) {
                    sqlTable.append(" " + map.get(key));
                } else {
                    sqlKey.append(key + ",");
                    sqlVaule.append("'" + map.get(key) + "'" + ",");
                }
            }
        }
        sqlTable.append(sqlKey.toString().substring(0, sqlKey.toString().lastIndexOf(",")) + ")").append(sqlVaule.toString().substring(0, sqlVaule.lastIndexOf(",")) + ")").append(";");
        String sql = sqlTable.toString();
        System.out.println("待执行的insert SQL：" + sql);
        return sql;
    }

    public static String updateSQL(String tableName, List<String> keyList, List<Student> valueList) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE " + tableName + " SET ");//equipmentassetstable
        for (int i = 0; i < keyList.size(); i++) {
            if (!"id".equals(keyList.get(i))) {
                sb.append(keyList.get(i));
                sb.append("=");
                sb.append("'" + valueList.get(i) + "'");
                if (i != keyList.size() - 1) {
                    sb.append(",");
                }
            }

        }
        for (int i = 0; i < keyList.size(); i++) {
            if ("id".equals(keyList.get(i))) {
                sb.append(" WHERE id = '" + valueList.get(i) + "'");
            }
        }
        sb.append(";");
        return sb.toString();
    }

    public static void output2SQLFile(String path, String fileName, String data) throws IOException {
        //创建文件对象
        File file = new File(path, fileName);
        //如果文件不存在则新建文件
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
        String[] split = data.split("!");
        //将数组的信息写入文件中
        for (int i = 0; i < split.length; i++) {
            bw.write(split[i]);
            bw.newLine();
            bw.flush();
        }
        bw.write("commit;");
        bw.newLine();
        bw.close();
    }

    public static void executeSqlFile(String path,String fileName) throws IOException, SQLException {
        //从文件中读取
        File file = new File(path, fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String contentLine  = br.readLine();
        String sql = "";
        while (contentLine != null) {
            sql += contentLine;
            contentLine = br.readLine();
        }
        System.out.println(sql);
        //创建数据源获取连接
//        Connection conn = dataSource.getConnection();
//        Statement state = conn.createStatement();
//        //添加一个批操作，相当于先缓存在本地
//        state.addBatch(sql);
//        //一次性执行一批sql语句
//        state.executeBatch();
//        //清空本地批操作
//        state.clearBatch();
//        //关闭连接
//        state.close();
//        conn.close();
    }

    public static void copySQLFile2TargetPath(String sourcePath, String sourceFileName, String[] targetPaths , String targerFileName) throws IOException {
        //将文件拷贝到目标目录
        File source = new File(sourcePath, sourceFileName);
        for (int i = 0; i < targetPaths.length; i++) {
            File destfile = new File(targetPaths[i], targerFileName);
            FileInputStream fis = new FileInputStream(source);
            if(!destfile.exists()){
                destfile.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(destfile);
            // 读写数据
            // 定义数组
            byte[] b = new byte[1024];
            // 定义长度
            int len;
            // 循环读取
            while ((len = fis.read(b))!=-1) {
                // 写出数据
                fos.write(b, 0 , len);
            }
            //关闭资源
            fos.close();
            fis.close();
        }
        //拷贝结束后删除源文件
        source.delete();
    }

    public static Map<String, Object> createMap(Student student, String sqlType) throws IllegalAccessException {
        Field[] fields = student.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
        if(sqlType != null && sqlType != ""){
            if (sqlType.equals("insert")) {
                LinkedHashMap<String, Object> insertMap = new LinkedHashMap<>();
                for (Field field : fields) {
                    //设置是否允许访问，不是修改原来的访问权限修饰词。
                    field.setAccessible(true);
                    //获取字段名，和字段的值
                    insertMap.put(field.getName(),field.get(student));
                }
                map.put("insertMap",insertMap);
            }
            if( sqlType.equals("update")) {
                ArrayList<String> keyList = new ArrayList<>();
                ArrayList<Object> valueList = new ArrayList<>();
                for (Field field : fields) {
                    //设置是否允许访问，不是修改原来的访问权限修饰词。
                    field.setAccessible(true);
                    //获取字段名，和字段的值
                    keyList.add(field.getName());
                    valueList.add(field.get(student));
                }
                map.put("keyList",keyList);
                map.put("valueList",valueList);
            }
        }
        System.out.println(map);
        return map;
    }

    public static void main(String[] args) throws Exception {
        //调用select的方法
//        Map<String,Object> map=new HashMap<>();
//        map.put("字段名","具体的值");
//        String tableName="表名";
//        System.out.println(selectQuerySQL(map,tableName).toString());
        //调用insert的方法
        Map<String,Object> map = new LinkedHashMap<>();
//        int id = 1;
        int id = 10;

        //模拟数据生成
        int time = 10;
        String sql = "";
        for (int i = 0; i < time; i++) {
            Student student = new Student();
            student.setId(++id);
            student.setName("kobe");
            student.setGender("0");
            student.setBirthday("2020-07-13");
            student.setScore(new BigDecimal(new DecimalFormat("0.00").format(100 - id)));
            Map<String, Object> insert = createMap(student, "insert");
            map = (Map<String, Object>) insert.get("insertMap");
            map.put("tableName", "stu");
//            map.put("id", id++);
//            map.put("name", "jordan");
//            map.put("gender", "0");
//            map.put("birthday", "2020-07-09");
//            map.put("score", "100.00");
//            map.put("tableName", "stu");
            //添加sql语句分割符号输出文件时换行时使用
            sql = sql + insertSQL(map) + "!";
        }

//        List<Student> studentList = new ArrayList<>();
//        for (int i = 0; i < time; i++) {
//            Student student = new Student();
//            student.setId(id--);
//            student.setName("jordan");
//            student.setGender("0");
//            student.setBirthday("2020-07-12");
//            student.setScore(new BigDecimal(new DecimalFormat("0.00").format(100 - id)));
//            studentList.add(student);
//        }
//        for (Student student : studentList) {
//            HashMap<String, Object> update = createMap(student, "update");
//            List<String> keyList = (List<String>)update.get("keyList");
//            List<Student> valueList = (List<Student>)update.get("valueList");
//            sql += updateSQL("stu", keyList, valueList) + "!";
//        }

        //将数据输出到文件
        //从配置文件中读取输出路径
        String path = PropertiesUtil.getValue("sqlfilepath");
        String fileName = "20200704.sql";
        output2SQLFile(path, fileName, sql);


        //批量执行文件中的sql语句
//        executeSqlFile(path, fileName);
        //将文件复制到指定目录下
        //从配置文件中读取文件路径
        String targerPath1 = PropertiesUtil.getValue("prepublishpath");
        String targerPath2 = PropertiesUtil.getValue("publishpaths");
        String[] targetPaths = {targerPath1, targerPath2};
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //以当前时间年月日作为文件名
        String targetFileName = sdf.format(new Date()) + ".sql";
        copySQLFile2TargetPath(path, fileName, targetPaths, targetFileName);
    }
}
