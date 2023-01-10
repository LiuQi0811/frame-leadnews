package com.frame.file.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/*
 *@ClassName OssAliYunConfigProperties
 *@Description oss 文件操作 配置读取类
 *@Author LiuQi
 *@Date 2023/1/10 09:30
 *@Version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
@ConfigurationProperties(prefix = "file.oss") //文件上传 配置前缀 file.oss
public class OssAliYunConfigProperties implements Serializable {

    /**
     * 站点
     */
    private String webSite;

    /**
     * 域名站点
     */
    private String endpoint;

    /**
     * 密钥id
     */
    private String accessKeyId;

    /**
     * 密钥
     */
    private String accessKeySecret;

    /**
     * 桶名称
     */
    private String bucketName;

    /**
     * 设置 OSSClient 允许打开的最大HTTP连接数，默认1024个
     */
    private Integer maxConnections;

    /**
     * 设置 Socket层传输数据的超时时间，默认50000毫秒
     */
    private Integer socketTimeout;

    /**
     * 设置建立连接的超时时间，默认50000毫秒
     */
    private Integer connectionTimeout;
    /**
     * 设置连接池中获取空闲的超时时间，超时则关闭连接
     */
    private Integer connectionRequestTimeout;

    /**
     * 设置连接空闲的超时时间，超时则关闭连接，默认60000毫秒
     */
    private Integer idleConnectionTime;

    /**
     * 设置失败请求重试次数，默认3次
     */
    private Integer maxErrorRetry;

    /**
     * 设置用户代理，指HTTP的User-Agent头，默认aliyun-sdk-java
     */
    private String userAgent;

    /**
     * 代理端口
     */
    private String proxyHost;

    /**
     * 代理账号
     */
    private String proxyUsername;

    /**
     * 代理密码
     */
    private String proxyPassword;

    /**
     * 白名单
     */
    private String whiteList;
}
