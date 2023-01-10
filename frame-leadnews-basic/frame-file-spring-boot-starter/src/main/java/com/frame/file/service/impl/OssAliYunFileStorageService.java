package com.frame.file.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.frame.file.config.OssAliYunConfigProperties;
import com.frame.file.service.FileStorageService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*
 *@ClassName OssAliyunFileStorageService
 *@Description TODO
 *@Author LiuQi
 *@Date 2023/1/10 09:28
 *@Version 1.0
 */
@Slf4j
@Component
public class OssAliYunFileStorageService implements FileStorageService {

    @Autowired
    private OSS ossClient;
    @Autowired
    private OssAliYunConfigProperties properties;


    /**
     * 文件拼接
     * @param dirPath
     * @param filename
     * @return
     */
    public String builderOssPath(String dirPath, String filename) {
        String separator = "/";
        StringBuilder builder = new StringBuilder(50);
        LocalDate localDate = LocalDate.now();
        String year = String.valueOf(localDate.getYear());
        builder.append(year).append(separator);
        String month = String.valueOf(localDate.getMonthValue());
        builder.append(month).append(separator);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String day = timeFormatter.format(localDate);
        builder.append(day.replace("-", "")).append(separator);
        builder.append(filename);
        return dirPath + separator + builder.toString();
    }

    /**
     * 文件上传
     * @param prefix 文件前缀
     * @param filename 文件名称
     * @param inputStream 文件流
     * @return
     */
    @Override
    public String store(String prefix, String filename, InputStream inputStream) {
        String url = ""; // 文件读取路径
        String key = this.builderOssPath(prefix, filename); // 设置文件路径和名称
        if (inputStream == null) { //判断文件
            log.info("上传文件：key {} 文件流为空 ", key);
            return url;
        }
        log.info("OSS 文件上传开始：{}", key);
        try {
            //获取 桶名称
            String bucketName = properties.getBucketName();
            ObjectMetadata objectMeta = new ObjectMetadata();
            objectMeta.setContentEncoding("UTF-8");
            objectMeta.setContentType("image/jpg");
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, objectMeta);
            // 设置该属性可以返回response。如果不设置，则返回的response为空。
            putObjectRequest.setProcess("true");
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            // 设置权限(公开读)
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (result != null) {
                log.info("OSS 文件上传成功：{}", key);
                url = key;
            }
        } catch (OSSException e) {
            log.info("OSS 文件上传错误：{}", e);
        } catch (ClientException e) {
            log.info("OSS 文件上传客户端错误：{}", e);
        }
        return url;
    }

    /**
     * 文件上传
     * @param prefix 文件前缀
     * @param filename 文件名称
     * @param contentType 文件类型 image/jpg 或 text/html
     * @param inputStream 文件流
     * @return
     */
    @Override
    public String store(String prefix, String filename, String contentType, InputStream inputStream) {
        return this.store(prefix, filename, inputStream);
    }

    /**
     * 文件删除
     * @param pathUrl 文件路径
     */
    @Override
    public void delete(String pathUrl) {
        String key = pathUrl.replace(properties.getWebSite(), "");
        List<String> keys = Lists.newArrayList();
        keys.add(key);
        ossClient.deleteObjects(new DeleteObjectsRequest(properties.getBucketName()).withKeys(keys)); //删除 deleteObjects
    }

    /**
     * 文件批量删除
     * @param pathUrls 文件路径集合
     */
    @Override
    public void deleteBatch(List<String> pathUrls) {
        ossClient.deleteObjects(new DeleteObjectsRequest(properties.getBucketName()).withKeys(pathUrls)); //删除 deleteObjects
    }

    /**
     * 下载文件
     * @param pathUrl 文件路径
     * @return
     */
    @Override
    public InputStream downloadFile(String pathUrl) {
        OSSObject ossObject = ossClient.getObject(properties.getBucketName(), pathUrl);
        InputStream inputStream = ossObject.getObjectContent();
        return inputStream;
    }

    /**
     * 获取文件文本内容
     * @param pathUrl 文件路径
     * @return
     * @throws IOException
     */
    @Override
    public String getFileContent(String pathUrl) throws IOException {
        return null;
    }
}
