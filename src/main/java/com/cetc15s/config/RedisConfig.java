package com.cetc15s.config;

import com.cetc15s.pojo.Student;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.net.UnknownHostException;
import java.time.Duration;

/**
 * @ClassName RedisConfig
 * @Description redis配置类
 * @Author bj
 * @Date 2020/8/2 0:04
 * @Version 1.0
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<Object, Student> studentRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Student> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        //设置序列化器
        Jackson2JsonRedisSerializer<Student> redisSerializer = new Jackson2JsonRedisSerializer<>(Student.class);
        template.setDefaultSerializer(redisSerializer);
        return template;
    }

    @Primary//当有多个缓存管理器是需要制定一个作为默认使用
    @Bean
    public RedisCacheManager studentCacheManager(RedisConnectionFactory redisConnectionFactory) {
        //设置序列化器
        Jackson2JsonRedisSerializer<Student> redisSerializer = new Jackson2JsonRedisSerializer<>(Student.class);
        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        redisSerializer.setObjectMapper(om);

        RedisCacheConfiguration cacheConfiguration =
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofDays(1))   // 设置缓存过期时间为一天
                        .disableCachingNullValues()     // 禁用缓存空值，不缓存null校验
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer));     // 设置CacheManager的值序列化方式为json序列化，可加入@Class属性
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(cacheConfiguration).build();     // 设置默认的cache组件
    }
}
