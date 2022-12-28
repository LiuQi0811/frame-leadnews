package com.frame.model.common;

import com.frame.model.common.enums.AppHttpCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/*
 *@ClassName ResponseResult
 *@Description 通用结果返回类
 *@Author LiuQi
 *@Date 2022/12/20 09:25
 *@Version 1.0
 */
@Data
@ApiModel(value = "通用结果返回类")
public class ResponseResult<T> implements Serializable {
    @ApiModelProperty(value = "状态码")
    private Integer code; //状态码
    @ApiModelProperty(value = "提示信息")
    private String errorMessage;//提示信息
    @ApiModelProperty(value = "数据")
    private T data; //数据

    public ResponseResult() {

    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.errorMessage = msg;
        this.data = data;
    }

    public static ResponseResult errorResult(Integer code, String msg) {
         ResponseResult result = new ResponseResult();
         return result.error(code,msg);
    }

    public static ResponseResult okResult(Integer code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.ok(code,null,msg);
    }

    public static ResponseResult okResult(Object data) {
        ResponseResult result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS);
        if(data !=null)
        {
         result.setData(data);
        }
        return result;
    }
    public static ResponseResult okResult(){
        return okResult(null);
    }
    public static ResponseResult errorResult(AppHttpCodeEnum enums){
        return  setAppHttpCodeEnum(enums,enums.getErrorMessage());
    }
    public static ResponseResult errorResult(AppHttpCodeEnum enums,String errorMessage){
        return setAppHttpCodeEnum(enums,errorMessage);
    }

    public static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums){
        return okResult(enums.getCode(),enums.getErrorMessage());
    }
    public static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums,String errorMessage){
        return okResult(enums.getCode(),errorMessage);
    }

    public ResponseResult<?> error(Integer code, String msg) {
        this.code = code;
        this.errorMessage = msg;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.errorMessage = msg;
        return this;
    }

    public ResponseResult<?> ok(T data) {
        this.data = data;
        return this;
    }

    public boolean checkCode() {
        if (this.getCode().intValue() != 0) {
            return false;
        }
        return true;
    }

}
