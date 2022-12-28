package com.frame.model.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*
 *@ClassName PageRequestDTO
 *@Description 分页返回数据封装
 *@Author LiuQi
 *@Date 2022/12/20 09:02
 *@Version 1.0
 */
@Data
@ApiModel(value = "分页返回数据封装")
public class PageRequestDTO
{
    /**
     * 当前页*
     */
    @ApiModelProperty(value = "当前页")
    private Integer page;
    /**
     * 每页显示条数*
     */
    @ApiModelProperty(value = "每页显示条数")
    private Integer size;

    public void checkParam(){
     if (this.page == null || this.page <= 0){//如果当前页 等于null 或者 小于等于0 默认第一页
            this.setPage(1);
     }
     if(this.size == null || this.size <=0 || this.size > 100)
     {//如果每页显示条数 等于null 或者 小于等于0 或者 大于100 默认显示10条
            this.setSize(10);
     }
    }

}
