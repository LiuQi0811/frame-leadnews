package com.frame.feign.client;

import com.frame.feign.client.fallback.WeMediaFeignFallback;
import com.frame.model.common.ResponseResult;
import com.frame.model.wemedia.pojo.WmUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 *@ClassName WeMediaFeignClient
 *@Description 自媒体 API Feign调用
 *@Author LiuQi
 *@Date 2022/12/25 13:38
 *@Version 1.0
 */
@FeignClient(value = "leadnews-wemedia", //要调取的远程微服务名称
        fallbackFactory = WeMediaFeignFallback.class,//fallbackFactory 服务降级实现类
        configuration = FeignAutoConfiguration.class // feign 配置信息 日志级别

)
public interface WeMediaFeignClient {

    /**
     * 根据用户名查询自媒体用户
     *
     * @param name 用户名
     * @return
     */
    @RequestMapping(value = "/api/v1/user/findByName/{name}", method = RequestMethod.GET)
    ResponseResult<WmUser> findByName(@PathVariable(value = "name") String name);


    /**
     * 保存自媒体用户
     *
     * @param wmUser
     * @return
     */
    @RequestMapping(value = "/api/v1/user/save", method = RequestMethod.POST)
    ResponseResult<WmUser> save(@RequestBody WmUser wmUser);

}
