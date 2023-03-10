package com.frame.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frame.model.common.ResponseResult;
import com.frame.model.user.dto.AuthDTO;
import com.frame.model.user.pojo.ApUserRealName;

/*
 *@ClassName ApUserRealNameService
 *@Description 用户实名认证 逻辑层
 *@Author LiuQi
 *@Date 2022/12/24 18:49
 *@Version 1.0
 */
public interface ApUserRealNameService extends IService<ApUserRealName> {
    /**
     * 根据状态查询需要认证相关的用户信息
     * @param authDTO
     * @return
     */
    ResponseResult loadListByStatus(AuthDTO authDTO);

    /**
     * 根据状态进行审核
     * @param authDTO
     * @param status 2 审核失败 9审核成功
     * @return
     */
    ResponseResult updateStatusById(AuthDTO authDTO,String status);
}
