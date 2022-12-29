package com.frame.user;

import com.alibaba.fastjson.JSON;
import com.frame.feign.client.ArticleFeignClient;
import com.frame.feign.client.WeMediaFeignClient;
import com.frame.model.article.pojo.ApAuthor;
import com.frame.model.common.ResponseResult;
import com.frame.model.wemedia.pojo.WmUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


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
        log.info(" {}", responseResult);
        log.info("获取结果对象 {}", JSON.toJSON(responseResult.getData()));
    }

    @Test
    public void saveWmUser() {
        WmUser wmUser = WmUser.builder()
                .name("twfx")
                .apUserId("1")
                .nickName("天外飞仙")
                .salt("abc")
                .password("hhhhh")
                .phone("13173478377")
                .email("398904988@qq.com")
                .score("0")
                .type("0")
                .build();
        weMediaFeignClient.save(wmUser);
    }


    @Autowired
    private ArticleFeignClient articleFeignClient;

    @Test
    public void findApAuthorByUserId() {
        ResponseResult<ApAuthor> responseResult = articleFeignClient.findByUserId("1");
        log.info(" {}", responseResult);
        log.info("获取结果对象 {}", JSON.toJSON(responseResult.getData()));
    }

    @Test
    public void saveApAutor(){
        ApAuthor apAuthor = ApAuthor.builder()
                .userId("1")
                .type("2")
                .name("树下一只梅")
                .WmUserId("d3029be56c79450996c05907931b671b")
                .build();
        log.info("参数 {}",JSON.toJSON(apAuthor));
        ResponseResult responseResult = articleFeignClient.save(apAuthor);
        log.info(" {}", responseResult);
    }
}
