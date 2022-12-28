package com.frame.model.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 *@ClassName AdChannel
 *@Description 频道信息对象
 *@Author LiuQi
 *@Date 2022/12/19 22:01
 *@Version 1.0
 */
@Data
@TableName(value = "ad_channel")
@ApiModel(value = "频道信息对象")
public class AdChannel implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * 频道名称*
     */
    @ApiModelProperty(value = "频道名称")
    @TableField(value = "name")
    private String name;
    /**
     * 频道描述*
     */
    @ApiModelProperty(value = "频道描述")
    @TableField(value = "description")
    private String description;
    /**
     * 是否默认频道*
     */
    @ApiModelProperty(value = "是否默认频道")
    @TableField(value = "is_default")
    private Boolean isDefault;
    /**
     * 频道状态*
     */
    @ApiModelProperty(value = "频道状态")
    @TableField(value = "status")
    private Boolean status;
    /**
     * 默认排序*
     */
    @ApiModelProperty(value = "默认排序")
    @TableField(value = "sort")
    private Integer sort;
    /**
     * 创建时间*
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    private LocalDateTime createTime;
}
