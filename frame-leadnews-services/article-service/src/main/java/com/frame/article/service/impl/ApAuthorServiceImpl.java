package com.frame.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.article.mapper.ApAuthorMapper;
import com.frame.article.service.ApAuthorService;
import com.frame.model.article.pojo.ApAuthor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/*
 *@ClassName ApAuthorServiceImpl
 *@Description 文章作者信息 逻辑实现层
 *@Author LiuQi
 *@Date 2022/12/28 13:33
 *@Version 1.0
 */
@Service
@Slf4j
public class ApAuthorServiceImpl extends ServiceImpl<ApAuthorMapper, ApAuthor> implements ApAuthorService {
    /**
     * 根据用户id查询作者信息
     *
     * @param userId
     * @return
     */
    @Override
    public ApAuthor findByUserId(String userId) {
        return this.getOne(Wrappers.<ApAuthor>lambdaQuery().eq(ApAuthor::getUserId, userId)); //社交账号 文章作者用户id 查询文章作者信息
    }

    /**
     * 保存文章作者信息
     *
     * @param apAuthor
     * @return
     */
    @Override
    public boolean insert(ApAuthor apAuthor) {
        apAuthor.setId(UUID.randomUUID().toString().replaceAll("-",""));//id
        apAuthor.setCreateTime(LocalDateTime.now());//创建时间
        return this.save(apAuthor);
    }
}
