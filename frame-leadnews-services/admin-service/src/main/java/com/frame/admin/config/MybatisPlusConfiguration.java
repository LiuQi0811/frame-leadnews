package com.frame.admin.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 *@ClassName MybatisPlusConfiguration
 *@Description MybatisPlus 配置相关
 *@Author LiuQi
 *@Date 2022/12/19 22:23
 *@Version 1.0
 */
@Configuration
public class MybatisPlusConfiguration {
    /**
     * MP分页配置插件
     *
     * @return
     */
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        return new PaginationInnerInterceptor();
    }
}
