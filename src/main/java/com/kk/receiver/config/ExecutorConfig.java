package com.kk.receiver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @Description : 异步线程池配置类
 * @Author : k.k
 * @Data : 2019/2/20
 */

@Configuration
@EnableAsync
public class ExecutorConfig {

    @Bean
    public Executor asyncServiceExecutor(){

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置核心线程数
        executor.setCorePoolSize(16);
        //设置最大允许线程数
        executor.setMaxPoolSize(20);
        //配置队列大小
        executor.setQueueCapacity(100000);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-kafkaProducer-service");
        //当pool已经达到max size的时候，不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();

        return executor;
    }

}
