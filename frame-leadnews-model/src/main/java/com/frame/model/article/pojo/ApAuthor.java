package com.frame.model.article.pojo;

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
 *@ClassName ApAuthor
 *@Description 文章作者信息
 *@Author LiuQi
 *@Date 2022/12/28 13:23
 *@Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "ap_author")
public class ApAuthor implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id*
     */
    @TableId(value = "id")
    @ApiModelProperty(value = "主键id")
    private String id;

    /**
     * 作者名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "作者名称")
    private String name;

    /**
     * 0 爬取数据 1 签约合作商 2 平台自媒体人
     */
    @TableField(value = "type")
    @ApiModelProperty(value = "0 爬取数据 1 签约合作商 2 平台自媒体人")
    private String type;

    /**
     * 社交账号 文章作者用户i
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "社交账号 文章作者用户id ")
    private String userId;


    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    /**
     * 自媒体账号id
     */
    @TableField(value = "wm_user_id")
    @ApiModelProperty(value = "自媒体账号id")
    private String WmUserId;
}
