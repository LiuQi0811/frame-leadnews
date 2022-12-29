package com.frame.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frame.model.article.pojo.ApAuthor;

/*
 *@ClassName ApAuthorService
 *@Description 文章作者信息 逻辑层
 *@Author LiuQi
 *@Date 2022/12/28 13:32
 *@Version 1.0
 */
public interface ApAuthorService extends IService<ApAuthor> {

    /**
     * 根据用户id查询作者信息
     * @param userId
     * @return
     */
     ApAuthor findByUserId(String userId);

    /**
     * 保存文章作者信息
     * @param apAuthor
     * @return
     */
     boolean  insert(ApAuthor apAuthor);
}
