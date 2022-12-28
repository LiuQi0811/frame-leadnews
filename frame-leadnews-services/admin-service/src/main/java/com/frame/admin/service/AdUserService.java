package com.frame.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frame.model.admin.dto.AdUserDTO;
import com.frame.model.admin.pojo.AdUser;
import com.frame.model.common.ResponseResult;

/*
 *@ClassName AdChannelService
 *@Description 平台管理人员信息 逻辑层
 *@Author LiuQi
 *@Date 2022/12/19 23:07
 *@Version 1.0
 */
public interface AdUserService extends IService<AdUser> {
    /**
     * 登录功能*
     * @param userDTO
     * @return data: {token: 凭证，user: 用户信息}
     */
    ResponseResult login(AdUserDTO userDTO);
}
