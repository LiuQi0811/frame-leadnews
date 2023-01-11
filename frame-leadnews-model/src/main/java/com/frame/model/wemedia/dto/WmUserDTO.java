package com.frame.model.wemedia.dto;

import lombok.Data;

/*
 *@ClassName WmUserDTO
 *@Description 自媒体用户信息 DTO
 *@Author LiuQi
 *@Date 2023/1/11 11:18
 *@Version 1.0
 */
@Data
public class WmUserDTO {
    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;
}
