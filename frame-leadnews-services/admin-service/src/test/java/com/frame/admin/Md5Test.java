package com.frame.admin;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

/*
 *@ClassName Md5Test
 *@Description TODO
 *@Author LiuQi
 *@Date 2022/12/22 13:25
 *@Version 1.0
 */
public class Md5Test
{
    @Test
    public void md5(){
        String salt = RandomStringUtils.randomAlphanumeric(10); //获取一个10位的随机字符串
        System.out.printf("生成盐的结果 %s\t",salt);
        String md5DigestAsHex = DigestUtils.md5DigestAsHex("www.uuu11.com".getBytes());
        System.out.printf("MD5加密后的结果 %s\t",md5DigestAsHex);

        String result = salt + "www.uuu11.com";
        System.out.printf("加盐后的MD5 %s\t",DigestUtils.md5DigestAsHex(result.getBytes()));

    }
}
