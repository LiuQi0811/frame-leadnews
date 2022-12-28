package com.frame.model.user.dto;

import com.frame.model.common.PageRequestDTO;
import lombok.Data;

/*
 *@ClassName AuthDTO
 *@Description TODO
 *@Author LiuQi
 *@Date 2022/12/24 18:42
 *@Version 1.0
 */
@Data
public class AuthDTO extends PageRequestDTO {
    /**
     * 认证用户id
     */
    private String id;
    /**
     * 驳回的信息
     */
    private String msg;
    /**
     * 状态
     */
    private String status;
}
