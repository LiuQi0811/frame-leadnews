package com.frame.file.config;

import com.aliyun.oss.*;
import com.aliyun.oss.common.comm.Protocol;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
    public OSS ossClient() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(properties.getEndpoint(), properties.getAccessKeyId(), properties.getAccessKeySecret());
        try {
            InputStream inputStream = new FileInputStream(filePath);
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            // 设置该属性可以返回response。如果不设置，则返回的response为空。
            putObjectRequest.setProcess("true");
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            // 如果上传成功，则返回200。
            System.out.println(result.getResponse().getStatusCode());
        } catch (OSSException oe) {
            log.info("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.info("Error Message:" + oe.getErrorMessage());
            log.info("Error Code:" + oe.getErrorCode());
            log.info("Request ID:" + oe.getRequestId());
            log.info("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            log.info("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.info("Error Message:" + ce.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return ossClient;
    }
}
