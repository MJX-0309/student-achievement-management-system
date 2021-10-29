package edu.zhku.boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author MJX
 * @date 2021/10/28
 */

@Configuration
public class ThreadPoolConfig {

    @Value("${thread.core-size}")
    private int coreSize;

    @Value("${thread.max-size}")
    private int maxSize;

    @Value("${thread.keep-alive-time}")
    private long keepAliveTime;
    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(coreSize,
                maxSize,
               keepAliveTime, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        return threadPoolExecutor;
    }
}
