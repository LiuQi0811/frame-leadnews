package com.frame.model.wemedia.vo;

import lombok.Data;

import java.time.LocalDateTime;

/*
 *@ClassName WmUserVO
 *@Description 自媒体用户信息 VO
 *@Author LiuQi
 *@Date 2023/1/11 11:20
 *@Version 1.0
 */
@Data
public class WmUserVO {
    private String id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String image;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 最后一次登录时间
     */
    private LocalDateTime loginTime;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
