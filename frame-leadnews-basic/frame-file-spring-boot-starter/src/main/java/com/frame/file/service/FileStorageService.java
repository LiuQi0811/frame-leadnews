package com.frame.file.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/*
 *@ClassName FileStorageService
 *@Description 文件上传 下载 删除 相关接口层
 *@Author LiuQi
 *@Date 2023/1/10 09:27
 *@Version 1.0
 */
public interface FileStorageService {
    /**
     * 文件上传
     * @param prefix 文件前缀
     * @param filename 文件名称
     * @param inputStream 文件流
     * @return
     */
    String store(String prefix, String filename, InputStream inputStream);

    /**
     * 文件上传
     * @param prefix 文件前缀
     * @param filename 文件名称
     * @param contentType 文件类型 image/jpg 或 text/html
     * @param inputStream 文件流
     * @return
     */
    String store(String prefix, String filename,String contentType, InputStream inputStream);

    /**
     * 文件删除
     * @param pathUrl 文件路径
     */
    void delete(String pathUrl);

    /**
     * 文件批量删除
     * @param pathUrls 文件路径集合
     */
    void deleteBatch(List<String> pathUrls);

    /**
     * 下载文件
     * @param pathUrl 文件路径
     * @return
     */
    InputStream downloadFile(String pathUrl);

    /**
     * 获取文件文本内容
     * @param pathUrl 文件路径
     * @return
     * @throws IOException
     */
    String getFileContent(String pathUrl) throws IOException;
}
