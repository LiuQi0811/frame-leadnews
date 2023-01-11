package com.frame.file.config;

import com.aliyun.oss.*;
import com.aliyun.oss.common.comm.Protocol;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.frame.file.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
 *@ClassName OssAliYunAutoConfig
 *@Description oss文件存储 自动配置类
 *@Author LiuQi
 *@Date 2023/1/10 09:29
 *@Version 1.0
 */
@Configuration
@EnableConfigurationProperties(value = OssAliYunConfigProperties.class) //开启文件配置读取
@Slf4j
@ConditionalOnClass(value = FileStorageService.class)
public class OssAliYunAutoConfig {
    @Autowired
    private OssAliYunConfigProperties properties;

    @Bean
    public ClientConfiguration clientConfiguration() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        // 设置 OSSClient 允许打开的最大HTTP连接数，默认1024个
        clientConfiguration.setMaxConnections(properties.getMaxConnections());
        // 设置 Socket层传输数据的超时时间，默认50000毫秒
        clientConfiguration.setSocketTimeout(properties.getSocketTimeout());
        // 设置建立连接的超时时间，默认50000毫秒
        clientConfiguration.setConnectionTimeout(properties.getConnectionTimeout());
        //  设置连接池中获取空闲的超时时间，超时则关闭连接
        clientConfiguration.setConnectionRequestTimeout(properties.getConnectionRequestTimeout());
        // 设置连接空闲的超时时间，超时则关闭连接，默认60000毫秒
        clientConfiguration.setIdleConnectionTime(properties.getIdleConnectionTime());
        // 设置失败请求重试次数，默认3次
        clientConfiguration.setMaxErrorRetry(properties.getMaxErrorRetry());
        // 设置是否开启二级域名的访问方式，默认不开启
        clientConfiguration.setSLDEnabled(false);
        // 设置 连接OSS所使用的协议(HTTP/HTTPS)，默认为HTTP
        clientConfiguration.setProtocol(Protocol.HTTP);
        // 设置用户代理，指HTTP的User-Agent头，默认aliyun-sdk-java
        clientConfiguration.setUserAgent(properties.getUserAgent());
        return clientConfiguration;
    }

    /**
     * OSS 客户端
     * @return
     */
    @Bean
    @ConditionalOnBean(value = ClientConfiguration.class)
    public OSS ossClient() {
        log.info("开始创建 OSS 客户端........");
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(properties.getEndpoint(), properties.getAccessKeyId(), properties.getAccessKeySecret());
        if(!ossClient.doesBucketExist(properties.getBucketName())){
            ossClient.createBucket(properties.getBucketName());
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(properties.getBucketName());
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        return ossClient;
    }
}
