package com.frame.user;

import com.alibaba.nacos.shaded.org.checkerframework.checker.index.qual.SameLen;
import com.frame.feign.client.WeMediaFeignClient;
import com.frame.model.common.ResponseResult;
import com.frame.model.wemedia.pojo.WmUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/*
 *@ClassName FeignTest
 *@Description 远程feign 接口调用测试
 *@Author LiuQi
 *@Date 2022/12/27 19:40
 *@Version 1.0
 */
@SpringBootTest
@Slf4j
public class FeignTest {

    @Autowired
    private WeMediaFeignClient weMediaFeignClient;

    @Test
    public void findWmUser() {
         ResponseResult<WmUser> responseResult = weMediaFeignClient.findByName("yunyouyou");
        log.info(" {}",responseResult);
    }
    @Test
    public void saveWmUser(){
        WmUser wmUser = WmUser.builder()
                .name("jsyy")
                .apUserId("2")
                .nickName("井上优雅")
                .salt("abc")
                .password(DigestUtils.md5DigestAsHex("adminabc".getBytes(StandardCharsets.UTF_8)))
                .phone("13173478377")
                .email("398904988@qq.com")
                .build();
        weMediaFeignClient.save(wmUser);
    }
}
