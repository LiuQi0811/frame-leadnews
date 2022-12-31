package com.frame.user;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/*
 *@ClassName YonYouAPI
 *@Description YonYouAPI
 *@Author LiuQi
 *@Date 2022/12/31 09:42
 *@Version 1.0
 */
@Slf4j
public class YonYouAPI {

    /**
     * API接口名称 - 身份证二要素验证【实名认证API】
     */
    public void idCardCheck(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders(); //请求头信息
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("apicode","xxxxxx");
        Map<String,String> paramType = new HashMap<>(); //请求参数
        paramType.put("userName","用户名");
        paramType.put("idNumber","身份证");
        // 参数一请求参数 转json String 参数二 请求头信息
        HttpEntity<String> httpEntity = new HttpEntity<>(JSON.toJSONString(paramType),httpHeaders); //请求对象

        // 参数一请求接口地址  参数二请求参数  参数三解析类型
        ResponseEntity<String> response = restTemplate.postForEntity("", httpEntity, String.class);
        //获取返回结果的内容
        final String body = response.getBody();
        log.info(body);
    }
}
