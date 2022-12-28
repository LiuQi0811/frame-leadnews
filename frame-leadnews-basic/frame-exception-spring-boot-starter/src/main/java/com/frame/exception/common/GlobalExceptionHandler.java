package com.frame.exception.common;

import com.frame.model.common.ResponseResult;
import com.frame.model.common.enums.AppHttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 *@ClassName exception
 *@Description TODO
 *@Author LiuQi
 *@Date 2022/12/21 08:50
 *@Version 1.0
 */
@Slf4j
@Configuration
@RestControllerAdvice //spring mvc 拦截异常处理注解
public class GlobalExceptionHandler {


    /**
     * 拦截所有异常*
     *
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseResult exception(Exception e) {
        e.printStackTrace();
        log.error("捕捉全局异常 {}", e.getMessage());
        return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"触发全局异常");
    }
    /**
     * 拦截自定义异常*
     *
     * @return
     */
    @ExceptionHandler(value = CustomExceptionHandler.class)
    public ResponseResult custom(CustomExceptionHandler e)
    {
        e.printStackTrace();
        log.error("捕捉自定义异常 {}", e.getMessage());
        return ResponseResult.errorResult(e.getAppHttpCodeEnum());
    }
}
