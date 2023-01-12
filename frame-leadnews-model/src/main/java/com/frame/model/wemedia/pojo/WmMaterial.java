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
 *@ClassName WmMaterial
 *@Description 自媒体图文信息
 *@Author LiuQi
 *@Date 2023/1/12 13:04
 *@Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "wm_material")
public class WmMaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(value = "id")
    @ApiModelProperty(value = "主键id")
    private String id;

    /**
     * 自媒体用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "自媒体用户id")
    private String userId;

    /**
     * 素材地址
     */
    @TableField(value = "url")
    @ApiModelProperty(value = "素材地址")
    private String url;

    /**
     * 素材类型 0图片 1视频
     */
    @TableField(value = "type")
    @ApiModelProperty(value = "素材类型 0图片 1视频")
    private String type;

    /**
     * 是否收藏
     */
    @TableField(value = "is_collection")
    @ApiModelProperty(value = "是否收藏")
    private String isCollection;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
