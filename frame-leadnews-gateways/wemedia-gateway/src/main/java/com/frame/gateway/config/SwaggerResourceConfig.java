package com.frame.gateway.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/*
 *@ClassName SwaggerResourceConfig
 *@Description swagger 配置
 *@Author LiuQi
 *@Date 2022/12/30 14:05
 *@Version 1.0
 */
@Slf4j
@Configuration
@Primary
@AllArgsConstructor
@EnableSwagger2
public class SwaggerResourceConfig implements SwaggerResourcesProvider {
    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;
    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>(); // 定义swagger资源 列表
        List<String> routes = new ArrayList<>(); //定义路由列表
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));//获取Gateway 路由信息 添加到路由列表
        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
                .forEach(route -> {
                    route.getPredicates().stream().filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                            .forEach(predicateDefinition -> resources.add(swaggerResource(route.getId(),
                                    predicateDefinition
                                            .getArgs()
                                            .get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                            .replace("**", "v2/api-docs"))));
                });
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        log.info("name {}  location {}", name, location);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
//
//    /**
//     * swagger2默认的url后缀
//     */
//    private static final String SWAGGER2_URL = "/v2/api-docs";
//
//    /**
//     * 路由定位器
//     */
//
//    @Autowired
//    private RouteLocator routeLocator;
//
//    /**
//     * 网关应用名称
//     */
//    @Value("${spring.application.name}")
//    private String gatewayName;
//
//    /**
//     * 获取 Swagger 资源
//     */
//    @Override
//    public List<SwaggerResource> get() {
//        //接口资源列表
//        List<SwaggerResource> resources = new ArrayList<>();
//        //服务名称列表
//        List<String> routeHosts = new ArrayList<>();
//
//        // 1. 获取路由Uri 中的Host=> 服务注册则为服务名=》app-service001
//        routeLocator.getRoutes()
//                .filter(route -> route.getUri().getHost() != null)
//                .filter(route -> !gatewayName.equals(route.getUri().getHost()))
//                .subscribe(route -> routeHosts.add(route.getUri().getHost()));
//
//        // 2. 创建自定义资源
//        Set<String> existsServer = new HashSet<>();     // 去重，多负载服务只添加一次
//        for (String routeHost : routeHosts) {
//            String serviceUrl = "/" + routeHost + SWAGGER2_URL; // 后台访问添加服务名前缀
//            if(!existsServer.contains(serviceUrl)){
//                existsServer.add(serviceUrl); //加过的放在set中
//                SwaggerResource swaggerResource = new SwaggerResource(); // 创建Swagger 资源
//                swaggerResource.setUrl(serviceUrl); // 设置访问地址
//                swaggerResource.setName(routeHost); // 设置名称
//                swaggerResource.setSwaggerVersion("2.0");
//                resources.add(swaggerResource);
//            }
//        }
//        return resources;
//    }

}
