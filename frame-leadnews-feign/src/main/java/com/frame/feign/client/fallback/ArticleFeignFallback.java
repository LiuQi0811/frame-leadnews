package com.frame.feign.client.fallback;

import com.frame.feign.client.ArticleFeignClient;
import com.frame.model.article.pojo.ApAuthor;
import com.frame.model.common.ResponseResult;
import com.frame.model.common.enums.AppHttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/*
 *@ClassName ArticleFeignFallback
 *@Description 文章作者信息 feign 服务降级
 *@Author LiuQi
 *@Date 2022/12/28 14:01
 *@Version 1.0
 */
@Component
@Slf4j
public class ArticleFeignFallback implements FallbackFactory<ArticleFeignClient> {

    @Override
    public ArticleFeignClient create(Throwable throwable) {
        throwable.printStackTrace();
        return new ArticleFeignClient() {
            @Override
            public ResponseResult<ApAuthor> findByUserId(String userId) {
                log.error("参数：{}",userId);
                log.error("根据用户id查询文章作者信息 ArticleFeignClient findByUserId 远程调用出错啦！ {}",throwable.getMessage());
                return ResponseResult.errorResult(AppHttpCodeEnum.REMOTE_SERVER_ERROR);
            }

            @Override
            public ResponseResult save(ApAuthor apAuthor) {
                log.error("参数：{}",apAuthor);
                log.error("保存文章作者信息 findByUserId save 远程调用出错啦！ {}",throwable.getMessage());
                return ResponseResult.errorResult(AppHttpCodeEnum.REMOTE_SERVER_ERROR);
            }
        };
    }
}
