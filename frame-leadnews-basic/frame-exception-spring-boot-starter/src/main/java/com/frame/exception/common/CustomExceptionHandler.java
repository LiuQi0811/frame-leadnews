package com.frame.exception.common;

import com.frame.model.common.enums.AppHttpCodeEnum;

/*
 *@ClassName CustomExceptionHandler
 *@Description TODO
 *@Author LiuQi
 *@Date 2022/12/21 09:41
 *@Version 1.0
 */
public class CustomExceptionHandler extends RuntimeException {
    private AppHttpCodeEnum appHttpCodeEnum; //异常处理的枚举

    public CustomExceptionHandler(AppHttpCodeEnum appHttpCodeEnum) {
        this.appHttpCodeEnum = appHttpCodeEnum;
    }

    public CustomExceptionHandler(AppHttpCodeEnum appHttpCodeEnum, String msg) {
        appHttpCodeEnum.setErrorMessage(msg);
        this.appHttpCodeEnum = appHttpCodeEnum;
    }

    public AppHttpCodeEnum getAppHttpCodeEnum() {
        return appHttpCodeEnum;

    }
}
