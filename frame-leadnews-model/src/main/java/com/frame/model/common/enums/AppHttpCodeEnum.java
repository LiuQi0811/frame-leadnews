package com.frame.model.common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/*
 *@ClassName AppHttpCodeEnum
 *@Description TODO
 *@Author LiuQi
 *@Date 2022/12/20 10:10
 *@Version 1.0
 */
@Getter
@AllArgsConstructor
public enum AppHttpCodeEnum {
    SUCCESS(0, "操作成功"),
    LOGIN_PASSWORD_ERROR(2, "密码错误"),
    LOGIN_STATUS_ERROR(3, "用户状态异常，请联系管理员"),
    PARAM_INVALID(50, "无效参数"),
    DATA_EXIST(1000, "数据已存在"),
    DATA_NOT_EXIST(1002, "数据不存在"),
    DATA_INSERT_ERROR(510, "数据保存失败"),
    DATA_NOT_ALLOW(1003, "数据不允许此操作"),
    REMOTE_SERVER_ERROR(5,"远程调用服务出错啦" );
    private Integer code;
    private String errorMessage;

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
