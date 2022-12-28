package com.frame.feign.config;

import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 *@ClassName FeignAutoConfiguration
 *@Description Feign日志相关
 *@Author LiuQi
 *@Date 2022/12/27 18:49
 *@Version 1.0
 */
@Configuration
@EnableFeignClients(value = "com.frame.feign.client")
@ComponentScan(value = "com.frame.feign.client.fallback")
public class FeignAutoConfiguration {
    @Bean
    Logger.Level level() {
        return Logger.Level.FULL;
    }
}
