package com.frame.feign.client;

import com.frame.feign.client.fallback.ArticleFeignFallback;
import com.frame.model.article.pojo.ApAuthor;
import com.frame.model.common.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 *@ClassName ArticleFeignClient
 *@Description 文章作者信息 API Feign调用
 *@Author LiuQi
 *@Date 2022/12/28 13:56
 *@Version 1.0
 */
@FeignClient(value = "leadnews-article", // 要调取的远程微服务名称
        fallbackFactory = ArticleFeignFallback.class,// fallbackFactory 服务降级实现类
        configuration = FeignAutoConfiguration.class // feign 配置信息 日志级别
)
public interface ArticleFeignClient {

    /**
     * 根据 ApUser id查询作者信息
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/api/v1/author/findByUserId/{userId}", method = RequestMethod.GET)
    ResponseResult<ApAuthor> findByUserId(@PathVariable(value = "userId") String userId);


    /**
     * 保存文章作者信息
     *
     * @param apAuthor
     * @return
     */
    @RequestMapping(value = "/api/v1/author/save", method = RequestMethod.POST)
    ResponseResult save(@RequestBody ApAuthor apAuthor);
}
