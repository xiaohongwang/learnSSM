package com.learn.pool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolConfig {


    @Bean("threadPoolExecutor")
    public Executor getThreadPool(){
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(5, 10,
                        10, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        return executor;
    }

    @Bean("taskExecutor")
    public Executor getTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(20);
        return taskExecutor;
    }

}
