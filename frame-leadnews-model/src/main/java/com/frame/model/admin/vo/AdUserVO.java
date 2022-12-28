package com.frame.model.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/*
 *@ClassName AdUserVO
 *@Description 管理人员返回信息封装
 *@Author LiuQi
 *@Date 2022/12/24 09:59
 *@Version 1.0
 */
@Data
@ApiModel(value = "管理人员返回信息封装")
public class AdUserVO {
    /**
     * id*
     */
    @ApiModelProperty(value = "id")
    private String id;
    /**
     * 登录名*
     */
    @ApiModelProperty(value = "登录名")
    private String name;
    /**
     * 昵称*
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;
    /**
     * 头像*
     */
    @ApiModelProperty(value = "头像")
    private String image;
    /**
     * 邮箱*
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 最后登录时间*
     */
    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime loginTime;
    /**
     * 创建时间*
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
