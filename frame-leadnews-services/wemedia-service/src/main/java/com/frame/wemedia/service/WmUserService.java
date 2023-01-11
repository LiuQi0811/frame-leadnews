package com.frame.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frame.model.common.ResponseResult;
import com.frame.model.wemedia.dto.WmUserDTO;
import com.frame.model.wemedia.pojo.WmUser;

/*
 *@ClassName WmUserService
 *@Description  自媒体用户信息 逻辑层
 *@Author LiuQi
 *@Date 2022/12/25 12:58
 *@Version 1.0
 */
public interface WmUserService extends IService<WmUser> {
    /**
     * 根据用户名查询自媒体用户
     * @param name 用户名
     * @return
     */
    ResponseResult findWeMediaUserByName(String name);

    /**
     * 保存自媒体用户
     * @param wmUser
     * @return
     */
    ResponseResult insert(WmUser wmUser);

    /**
     * 自媒体用户 登录
     * @param wmUserDTO
     * @return
     */
    ResponseResult login(WmUserDTO wmUserDTO);

}
