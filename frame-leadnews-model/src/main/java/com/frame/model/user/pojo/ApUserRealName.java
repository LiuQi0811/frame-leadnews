package com.frame.model.user.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
 *@ClassName ApUserRealName
 *@Description 实名认证信息
 *@Author LiuQi
 *@Date 2022/12/24 18:47
 *@Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "ap_user_realname")
public class ApUserRealName {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * 账号id
     */
    @ApiModelProperty(value = "账号id")
    @TableField(value = "user_id")
    private String userId;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    @TableField(value = "name")
    private String name;

    /**
     * 资源名称*
     */
    @ApiModelProperty(value = "资源名称")
    @TableField(value = "idno")
    private String idno;
    /**
     * 手持照片
     */
    @ApiModelProperty(value = "手持照片")
    @TableField(value = "hold_image")
    private String holdImage;

    /**
     * 活体照片
     */
    @ApiModelProperty(value = "活体照片")
    @TableField(value = "live_image")
    private String liveImage;
    /**
     * 状态 0创建中
     * 1 待审核
     * 2 审核失败
     * 9 审核通过
     */
    @ApiModelProperty(value = "状态 0创建中\n" +
            "     * 1 待审核\n" +
            "     * 2 审核失败\n" +
            "     * 9 审核通过")
    @TableField(value = "status")
    private String status;

    /**
     * 拒绝原因
     */
    @ApiModelProperty(value = "拒绝原因")
    @TableField(value = "reason")
    private String reason;


    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    private LocalDateTime createTime;
    /**
     * 提交时间
     */
    @ApiModelProperty(value = "提交时间")
    @TableField(value = "submit_time")
    private LocalDateTime submitTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
