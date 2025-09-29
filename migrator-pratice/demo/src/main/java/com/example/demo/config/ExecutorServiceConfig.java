package com.example.demo.config;

import jakarta.annotation.Nonnull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ExecutorServiceConfig {

    @Bean
    public ExecutorService fixedExecutor() {
        return new ThreadPoolExecutor(
                10,
                10,
                0L, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new ThreadFactoryBuilder("fixed-"),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    private static class ThreadFactoryBuilder implements ThreadFactory {

        private final String prefix;
        private int n = 0;

        ThreadFactoryBuilder(String prefix) {
            this.prefix = prefix;
        }

        public Thread newThread(@Nonnull Runnable r) {
            return new Thread(r, prefix + (n++));
        }
    }
}
