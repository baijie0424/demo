package com.cetc15s.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName ThreadPoolTaskConfig
 * @Description 自定义线程池
 * @Author bj
 * @Date 2020/7/30 0:37
 * @Version 1.0
 */
@Configuration
@EnableAsync
public class ThreadPoolTaskConfig {
    private static final int corePoolSize = 10;
    private static final int maxPoolSize = 100;
    private static final int keepAliveTime = 10;
    private static final int queueCapacity = 10;
    private static final String threadNamePrefix = "Task_Service";

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        //线程池对拒绝任务的处理策略采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在executor方法中调用线程中运行被拒绝的任务，如果执行程序已关闭则会丢弃该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //初始化
        executor.initialize();
        return executor;
    }

}
