package com.cetc15s;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author bj
 * @Description
 * @Date 2020/7/4 22:13
 * @Param
 * @Return 
 */
@EnableRabbit // 开启基于注解的rabbitmq
@EnableCaching // 开启缓存
@EnableAsync // 开启异步任务
@EnableScheduling // 开启定时任务
@MapperScan("com.cetc15s.dao") //配置mapper类扫描
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}