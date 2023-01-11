package com.frame.wemedia.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.exception.common.CustomExceptionHandler;
import com.frame.model.common.ResponseResult;
import com.frame.model.common.enums.AppHttpCodeEnum;
import com.frame.model.wemedia.dto.WmUserDTO;
import com.frame.model.wemedia.pojo.WmUser;
import com.frame.model.wemedia.vo.WmUserVO;
import com.frame.utils.common.AppJwtUtil;
import com.frame.wemedia.mapper.WmUserMapper;
import com.frame.wemedia.service.WmUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
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

    /**
     * 自媒体用户 登录
     *
     * @param wmUserDTO
     * @return
     */
    @Override
    public ResponseResult login(WmUserDTO wmUserDTO) {
        //校验参数 name password
        String name = wmUserDTO.getName();
        String password = wmUserDTO.getPassword();
        if (StringUtils.isAnyBlank(name, password)) {
            throw new CustomExceptionHandler(AppHttpCodeEnum.PARAM_INVALID, "用户名或者密码不能为空");
        }
        //根据用户名 查询用户
        ResponseResult<WmUser> responseResult = this.findWeMediaUserByName(name);
        if (responseResult.getData() == null) {
          throw new CustomExceptionHandler(AppHttpCodeEnum.DATA_NOT_EXIST,"用户不存在");
        }
        WmUser wmUser = responseResult.getData();
        //判断输入的密码 和 数据库储存的密码是否一致
        String md5DigestAsHex = DigestUtils.md5DigestAsHex((wmUserDTO.getPassword() + wmUser.getSalt()).getBytes(StandardCharsets.UTF_8));
        if (!md5DigestAsHex.equals(wmUser.getPassword())){
            throw new CustomExceptionHandler(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }
        //判断用户状态
        if(!wmUser.getStatus().equals("9")){
            throw new CustomExceptionHandler(AppHttpCodeEnum.LOGIN_STATUS_ERROR);
        }
        //修改 最后的登录时间
        wmUser.setLoginTime(LocalDateTime.now());
        //颁发token 凭证
        String token = AppJwtUtil.getToken(Long.valueOf(wmUser.getId()));
        WmUserVO wmUserVO = new WmUserVO();
        BeanUtils.copyProperties(wmUser,wmUserVO);
        Map<String,Object> result =new HashMap<>();
        result.put("token",token);
        result.put("user",wmUserVO);
        return ResponseResult.okResult(result);
    }
}
