package com.frame.wemedia.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.exception.common.CustomExceptionHandler;
import com.frame.model.common.ResponseResult;
import com.frame.model.common.enums.AppHttpCodeEnum;
import com.frame.model.wemedia.pojo.WmUser;
import com.frame.wemedia.mapper.WmUserMapper;
import com.frame.wemedia.service.WmUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

/*
 *@ClassName WmUserServiceImpl
 *@Description 自媒体用户信息 逻辑实现层
 *@Author LiuQi
 *@Date 2022/12/25 12:59
 *@Version 1.0
 */
@Service
public class WmUserServiceImpl extends ServiceImpl<WmUserMapper, WmUser> implements WmUserService {
    /**
     * 根据用户名查询自媒体用户
     *
     * @param name 用户名
     * @return
     */
    @Override
    public ResponseResult findWeMediaUserByName(String name) {
        WmUser wmUser = this.getOne(Wrappers.<WmUser>lambdaQuery().eq(WmUser::getName, name));
//        if (wmUser == null) {
//            throw new CustomExceptionHandler(AppHttpCodeEnum.DATA_NOT_EXIST, "用户信息不存在");
//        }
        return ResponseResult.okResult(wmUser);
    }


    /**
     * 保存自媒体用户
     *
     * @param wmUser
     * @return
     */
    @Override
    public ResponseResult insert(WmUser wmUser) {
        String salt = wmUser.getSalt();//获取盐
        String password = salt + wmUser.getPassword();
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));//密码加密
        wmUser.setPassword(md5DigestAsHex);//加密后密码赋值
        wmUser.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        wmUser.setCreateTime(LocalDateTime.now());
        boolean result = this.save(wmUser);
        if (!result) {
            throw new CustomExceptionHandler(AppHttpCodeEnum.DATA_INSERT_ERROR);
        }
        return ResponseResult.okResult(wmUser);
    }
}
