package com.cetc15s.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName Dog
 * @Description @PropertySource("classpath:dog.properties")可以用来加载指定配置文件
 * @Author bj
 * @Date 2020/7/5 1:18
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "dog")
@PropertySource("classpath:dog.properties")
public class Dog {
    private String name;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
