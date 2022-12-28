package com.frame.feign.client.fallback;

import com.frame.feign.client.WeMediaFeignClient;
import com.frame.model.common.ResponseResult;
import com.frame.model.common.enums.AppHttpCodeEnum;
import com.frame.model.wemedia.pojo.WmUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/*
 *@ClassName WeMediaFeignFallback
 *@Description 服务降级
 *@Author LiuQi
 *@Date 2022/12/27 18:55
 *@Version 1.0
 */
@Component
@Slf4j
public class WeMediaFeignFallback implements FallbackFactory<WeMediaFeignClient> {
    @Override
    public WeMediaFeignClient create(Throwable throwable) {
        throwable.printStackTrace();
        return new WeMediaFeignClient() {
            @Override
            public ResponseResult<WmUser> findByName(String name) {
                log.error("参数：{}",name);
                log.error("根据用户名查询自媒体用户 远程调用出错啦！ {}",throwable.getMessage());
                return ResponseResult.errorResult(AppHttpCodeEnum.REMOTE_SERVER_ERROR);
            }

            @Override
            public ResponseResult<WmUser> save(WmUser wmUser) {
                log.error("参数：{}",wmUser);
                log.error("保存自媒体用户 远程调用出错啦！ {}",throwable.getMessage());
                return ResponseResult.errorResult(AppHttpCodeEnum.REMOTE_SERVER_ERROR);
            }
        };
    }
}
