package com.frame.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.admin.mapper.AdUserMapper;
import com.frame.admin.service.AdUserService;
import com.frame.exception.common.CustomExceptionHandler;
import com.frame.model.admin.dto.AdUserDTO;
import com.frame.model.admin.pojo.AdUser;
import com.frame.model.admin.vo.AdUserVO;
import com.frame.model.common.ResponseResult;
import com.frame.model.common.enums.AppHttpCodeEnum;
import com.frame.utils.common.AppJwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.frame.model.common.enums.AppHttpCodeEnum.LOGIN_PASSWORD_ERROR;

/*
 *@ClassName AdUserServiceImpl
 *@Description 平台管理人员信息 逻辑实现层
 *@Author LiuQi
 *@Date 2022/12/24 08:21
 *@Version 1.0
 */
@Service
public class AdUserServiceImpl extends ServiceImpl<AdUserMapper, AdUser> implements AdUserService {
    /**
     * 登录功能*
     *
     * @param userDTO
     * @return data: {token: 凭证，user: 用户信息}
     */
    @Override
    public ResponseResult login(AdUserDTO userDTO) {
        //校验参数 保证 name password 不为空
        String name = userDTO.getName();
        String password = userDTO.getPassword();
        if (StringUtils.isAnyBlank(name, password)) {
            throw new CustomExceptionHandler(AppHttpCodeEnum.PARAM_INVALID, "用户名或密码不能为空");
        }
        //根据name 查询用户信息
        AdUser adUser = this.getOne(Wrappers.<AdUser>lambdaQuery().eq(AdUser::getName, name));
        if (adUser == null) {
            throw new CustomExceptionHandler(AppHttpCodeEnum.DATA_NOT_EXIST, "用户不存在");
        }
        //判断输入的密码是否和数据库中的密码 一致
        String passwordMD5 = password + adUser.getSalt(); //密码 + 盐
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(passwordMD5.getBytes(StandardCharsets.UTF_8));
        if (!md5DigestAsHex.equals(adUser.getPassword())) {
            throw new CustomExceptionHandler(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR, "用户密码错误");
        }

        //判断用户状态是否正确 9(正常状态)
        String status = adUser.getStatus();
        if (!status.equals("9")) {
            throw new CustomExceptionHandler(AppHttpCodeEnum.LOGIN_STATUS_ERROR);
        }
        //修改最近登录时间
        adUser.setLoginTime(LocalDateTime.now());
        this.updateById(adUser);
        //颁发token
        String token = AppJwtUtil.getToken(Long.valueOf(adUser.getId()));
        //封装返回结果 token user
        Map<String, Object> resultMap = new HashMap<>();
        AdUserVO adUserVO = new AdUserVO();
        //通过 BeanUtils拷贝同名 属性
        BeanUtils.copyProperties(adUser, adUserVO);
        resultMap.put("token", token);
        resultMap.put("user", adUserVO);
        return ResponseResult.okResult(resultMap);
    }
}
