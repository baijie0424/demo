package com.cetc15s.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName AMQPConfig
 * @Description rabbitmq配置类
 * @Author bj
 * @Date 2020/8/2 23:43
 * @Version 1.0
 */
@Configuration
public class AMQPConfig {

    @Bean
    public MessageConverter messageConverter() {
        //使用json序列化代替默认的java序列化
        return new Jackson2JsonMessageConverter();
    }
}
