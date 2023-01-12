package com.frame.knife4j.config;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 *@ClassName Swagger2Configuration
 *@Description knife4j swagger文档配置
 *@Author LiuQi
 *@Date 2022/12/30 19:03
 *@Version 1.0
 */
@EnableKnife4j
@Configuration
@EnableSwagger2
//@EnableSwaggerBootstrapUI
@Import(value = BeanValidatorPluginsConfiguration.class)
public class Swagger2Configuration {
    @Value("${spring.application.name}")
    private String group;



    @Bean(value = "defaultApi2")
    @Order(value = 1)
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(group)//分组名称
                .apiInfo(apiInfo())
                .select()
                // 要扫描的API(Controller)基础包
                .apis(RequestHandlerSelectors.basePackage("com.frame"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Frame 平台API管理文档")
                .description("平台管理服务api")
                .contact(new Contact("冰糖雪梨", "", ""))
                .version("1.0.0")
                .build();
    }
    }
