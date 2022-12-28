package com.frame.model.admin.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*
 *@ClassName AdUserDTO
 *@Description 登录参数 封装
 *@Author LiuQi
 *@Date 2022/12/24 08:17
 *@Version 1.0
 */
@Data
@ApiModel(value = "登录参数 封装")
public class AdUserDTO
{

    /**
     * 登录用户名*
     */
    @ApiModelProperty(value = "登录用户名")
    private String name;

    /**
     * 登录密码*
     */
    @ApiModelProperty(value = "登录密码")
    private String password;
}
