package com.frame.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.frame.gateway.util.AppJwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *@ClassName AuthorizeFilter
 *@Description 认证过滤器
 *@Author LiuQi
 *@Date 2022/12/24 10:31
 *@Version 1.0
 */
@Component
@Order(-1) //值越小越优先执行
@Slf4j
public class AuthorizeFilter implements GlobalFilter {
    //白名单列表
    private static List<String> writesUrl = new ArrayList<>();

    //初始化白名单url路径
    static {
        writesUrl.add("/login/in");
        writesUrl.add("/v2/api-docs");
    }

    /**
     * * 过滤方法
     *
     * @param exchange
     * @param chain    过滤器链
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();//获取请求对象
        ServerHttpResponse response = exchange.getResponse();//获取相应对象
        //获取用户请求的uri的地址
        String path = request.getURI().getPath();
        //判断该uri是否属于白名单路径 如果属于放行
        for (String allowUri : writesUrl) {
            if (path.contains(allowUri)) {

                return chain.filter(exchange); //放行
            }
        }
        //获取请求头的token 如果为空 返回401并终止请求
        String token = request.getHeaders().getFirst("token");
        if (StringUtils.isBlank(token)) {
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);//返回401状态
//            return response.setComplete();//终止请求
            return writeMessage(exchange,"token 不能为空");
        }
        // 校验并解析token
        try {
            Claims claimsBody = AppJwtUtil.getClaimsBody(token);
            int verifyToken = AppJwtUtil.verifyToken(claimsBody);
            if (verifyToken < 1) {
                //token 有效
                //如果解析成功 获取token中存放的用户id 并且将用户id设置到请求头中 路由给其他微服务
                Object id = claimsBody.get("id");
                //将用户id设置到请求头中 路由给其他微服务
                request.mutate().header("userId", String.valueOf(id)).build();
                return chain.filter(exchange); //认证成功放行请求
            } else {
                log.error("解析token失败 原因：token已过期");
                return writeMessage(exchange,"解析token失败 原因：token已过期");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("解析token失败 原因：{}", e.getMessage());
            return writeMessage(exchange,"解析token失败 原因："+e.getMessage());
        }

//        //如果解析失败 返回401 并终止请求
//        response.setStatusCode(HttpStatus.UNAUTHORIZED);//返回401状态
//        return response.setComplete();//终止请求
    }

    /**
     * * 返回错误提示信息
     *
     * @param exchange
     * @param message
     * @return
     */
    private Mono<Void> writeMessage(ServerWebExchange exchange, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("code",HttpStatus.UNAUTHORIZED.value());
        map.put("errorMessage",message);
        //获取响应对象
         ServerHttpResponse response = exchange.getResponse();
         //设置响应状态码
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //设置返回类型
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        //设置返回数据
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSON.toJSONBytes(map));
        //响应数据回浏览器
        return response.writeWith(Flux.just(dataBuffer));
    }
}
