package com.cetc15s.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName PropertiesUtil
 * @Description
 * @Author bj
 * @Date 2020/7/10 1:01
 * @Version 1.0
 */
public class PropertiesUtil {
    public static final String FILE_PATH = "filepath.properties";

    //通过传入的路径及key，获得对应的值
    public static String getValue(String path, String key)
    {
        Properties properties = new Properties();
        try
        {
            properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(path));
        }
        catch (IOException e)
        {
            throw new RuntimeException("File Read Failed...", e);
        }
        return properties.getProperty(key);
    }
    //通过key直接获取对应的值
    public static String getValue(String key)
    {
        Properties properties = new Properties();
        try
        {
            properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(FILE_PATH));
        }
        catch (IOException e)
        {
            throw new RuntimeException("File Read Failed...", e);
        }
        return properties.getProperty(key);
    }
}
