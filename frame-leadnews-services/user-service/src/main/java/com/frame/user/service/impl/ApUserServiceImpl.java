package com.frame.user.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.model.user.pojo.ApUser;
import com.frame.user.mapper.ApUserMapper;
import com.frame.user.service.ApUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
 *@ClassName ApUserRealNameService
 *@Description  用户信息 逻辑实现层
 *@Author LiuQi
 *@Date 2022/12/24 18:49
 *@Version 1.0
 */
@Service
@Slf4j
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper,ApUser> implements ApUserService {

}
