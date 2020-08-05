package com.cetc15s.config;

import com.cetc15s.pojo.ConfigBO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyAppConfig
 * @Description 给容器中添加组件，添加的组件就是@Bean标记方法的返回值，容器中这个组件的默认id就是方法名
 * @Author bj
 * @Date 2020/7/5 1:40
 * @Version 1.0
 */
@Configuration
public class MyAppConfig {

    @Bean
    public ConfigBO myConfig() {
        return new ConfigBO();
    }

}
