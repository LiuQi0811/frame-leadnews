package com.frame.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.exception.common.CustomExceptionHandler;
import com.frame.model.common.PageResponseResult;
import com.frame.model.common.ResponseResult;
import com.frame.model.common.enums.AppHttpCodeEnum;
import com.frame.model.user.dto.AuthDTO;
import com.frame.model.user.pojo.ApUserRealName;
import com.frame.user.mapper.ApUserRealNameMapper;
import com.frame.user.service.ApUserRealNameService;
import org.springframework.stereotype.Service;

/*
 *@ClassName ApUserRealNameServiceImpl
 *@Description TODO
 *@Author LiuQi
 *@Date 2022/12/24 18:49
 *@Version 1.0
 */
@Service
public class ApUserRealNameServiceImpl extends ServiceImpl<ApUserRealNameMapper, ApUserRealName> implements ApUserRealNameService {

    /**
     * 根据状态查询需要认证相关的用户信息
     *
     * @param authDTO
     * @return
     */
    @Override
    public ResponseResult loadListByStatus(AuthDTO authDTO) {
        //校验参数 分页
        if (authDTO==null) {
            throw new CustomExceptionHandler(AppHttpCodeEnum.PARAM_INVALID,"");
        }
        authDTO.checkParam();
        //封装查询条件
        Page<ApUserRealName> pageReq = new Page<>(authDTO.getPage(), authDTO.getSize());
        LambdaQueryWrapper<ApUserRealName> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(authDTO.getStatus() != null, ApUserRealName::getStatus, authDTO.getStatus());
        //执行查询 封装查询条件
        Page<ApUserRealName> pageResult = this.page(pageReq, queryWrapper);

        return new PageResponseResult(authDTO.getPage(), authDTO.getSize(), pageResult.getTotal(), pageResult.getRecords());
    }
}
