package com.frame.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/*
 *@ClassName PageResponseResult
 *@Description TODO
 *@Author LiuQi
 *@Date 2022/12/20 10:51
 *@Version 1.0
 */
@Data
@AllArgsConstructor
@Builder
public class PageResponseResult extends ResponseResult{
    private Integer currentPage;
    private Integer size;
    private Long total;
    private Object data;
}
