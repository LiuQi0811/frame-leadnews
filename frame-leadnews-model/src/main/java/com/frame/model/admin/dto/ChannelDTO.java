package com.frame.model.admin.dto;

import com.frame.model.common.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*
 *@ClassName ChannelDto
 *@Description 频道参数封装类
 *@Author LiuQi
 *@Date 2022/12/19 23:11
 *@Version 1.0
 */
@Data
@ApiModel(value = "频道参数封装类")
public class ChannelDTO extends PageRequestDTO
{
    /**
     * 频道名称*
     */
    @ApiModelProperty(value = "频道名称")
    private String name;
    /**
     * 频道状态*
     */
    @ApiModelProperty(value = "频道状态")
    private Integer status;

}
