package com.frame.wemedia;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自媒体服务 启动类
 */
@SpringBootApplication
@MapperScan(value = "com.frame.wemedia.mapper")
public class WeMediaApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeMediaApplication.class, args);
    }
}
