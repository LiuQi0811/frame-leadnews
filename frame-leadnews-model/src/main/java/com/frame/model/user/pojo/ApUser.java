package com.frame.model.user.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 *@ClassName ApUser
 *@Description 用户信息
 *@Author LiuQi
 *@Date 2022/12/28 18:30
 *@Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "ap_user")
public class ApUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * 密码,通信等加密盐
     */
    @TableField(value = "salt")
    @ApiModelProperty(value = "密码,通信等加密盐")
    private String salt;
    /**
     * 用户名
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "用户名")
    private String name;

    /**
     * 密码,md5加密
     */
    @TableField(value = "password")
    @ApiModelProperty(value = "密码,md5加密")
    private String password;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 头像
     */
    @TableField(value = "image")
    @ApiModelProperty(value = "头像")
    private String image;

    /**
     * 性别 0 男  1 女
     */
    @TableField(value = "sex")
    @ApiModelProperty(value = " 性别 0 男  1 女")
    private String sex;

    /**
     * 认证状态 0 未 1是
     */
    @TableField(value = "is_certification")
    @ApiModelProperty(value = "0 未 1是")
    private String isCertification;

    /**
     * 是否身份认证
     */
    @TableField(value = "is_identity_authentication")
    @ApiModelProperty(value = "是否身份认证")
    private String isIdentityAuthentication;

    /**
     * 0 正常 1锁定
     */
    @TableField(value = "status")
    @ApiModelProperty(value = "0 正常 1锁定")
    private String status;

    /**
     * 0 普通用户 1自媒体人 2 大V
     */
    @TableField(value = "flag")
    @ApiModelProperty(value = "0 普通用户 1自媒体人 2 大V")
    private String flag;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
