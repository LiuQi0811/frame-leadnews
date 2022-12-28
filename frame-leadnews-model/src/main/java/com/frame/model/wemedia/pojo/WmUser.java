package com.frame.model.wemedia.pojo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 *@ClassName WmUser
 *@Description 自媒体用户信息
 *@Author LiuQi
 *@Date 2022/12/25 12:37
 *@Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "wm_user")
public class WmUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id*
     */
    @TableId(value = "id")
    @ApiModelProperty(value = "主键id")
    private String id;
    /**
     * 主键id*
     */
    @TableField(value = "ap_user_id")
    @ApiModelProperty(value = "主键id")
    private String apUserId;
    /**
     * 登录用户名
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "登录用户名")
    private String name;
    /**
     * 登录密码
     */
    @TableField(value = "password")
    @ApiModelProperty(value = "登录密码")
    private String password;
    /**
     * 盐
     */
    @TableField(value = "salt")
    @ApiModelProperty(value = "盐")
    private String salt;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 头像
     */
    @TableField(value = "image")
    @ApiModelProperty(value = "头像")
    private String image;

    /**
     * 归属地
     */
    @TableField(value = "location")
    @ApiModelProperty(value = "归属地")
    private String location;


    /**
     * 手机号
     */
    @TableField(value = "phone")
    @ApiModelProperty(value = "手机号")
    private String phone;


    /**
     * 状态 0 暂时不可用  1 永久不可用  9 正常可用*
     */
    @TableField(value = "status")
    @ApiModelProperty(value = "状态\n" +
            "0 暂时不可用\n" +
            "1 永久不可用\n" +
            "9 正常可用 ")
    private String status;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;


    /**
     * 账号类型 0个人
     * 1 企业
     * 2 子账号
     */
    @TableField(value = "type")
    @ApiModelProperty(value = "账号类型 0个人\n" +
            "      1 企业\n" +
            "      2 子账号")
    private String type;
    /**
     * 运营评分
     */
    @TableField(value = "score")
    @ApiModelProperty(value = "运营评分")
    private String score;
    /**
     * 最后一次登录时间*
     */
    @TableField(value = "login_time")
    @ApiModelProperty(value = "最后一次登录时间")
    private LocalDateTime loginTime;

    /**
     * 创建时间*
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
