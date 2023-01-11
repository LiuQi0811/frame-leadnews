package com.frame.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.*;

import java.util.Optional;

/*
 *@ClassName SwaggerHandlerController
 *@Description 网关创建文档
 *@Author LiuQi
 *@Date 2022/12/30 16:28
 *@Version 1.0
 */
@RestController
public class SwaggerHandlerController
{
    @Autowired(required = false)
    private SecurityConfiguration securityConfiguration;

    @Autowired(required = false)
    private UiConfiguration uiConfiguration;

    private final SwaggerResourcesProvider swaggerResourcesProvider;

    @Autowired
    public SwaggerHandlerController(SwaggerResourcesProvider swaggerResourcesProvider){
        this.swaggerResourcesProvider = swaggerResourcesProvider;
    }

    @RequestMapping(value = "/swagger-resources",method = RequestMethod.GET)
    public Mono<ResponseEntity> swaggerResources(){
        return Mono.just(new ResponseEntity<>(
                swaggerResourcesProvider.get(),
                HttpStatus.OK
        ));
    }

    @RequestMapping(value = "/swagger-resources/configuration/security",method = RequestMethod.GET)
    public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration(){
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(securityConfiguration)
                        .orElse(SecurityConfigurationBuilder.builder().build()),
                HttpStatus.OK
        ));
    }

    @RequestMapping(value = "/swagger-resources/configuration/ui",method = RequestMethod.GET)
    public Mono<ResponseEntity<UiConfiguration>> uiConfiguration(){
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(uiConfiguration)
                        .orElse(UiConfigurationBuilder.builder().build()),
                HttpStatus.OK
        ));
    }




}
