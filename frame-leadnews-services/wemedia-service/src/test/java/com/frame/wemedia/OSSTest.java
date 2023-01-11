package com.frame.wemedia;

import com.frame.file.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/*
 *@ClassName OSSTest
 *@Description TODO
 *@Author LiuQi
 *@Date 2023/1/11 10:45
 *@Version 1.0
 */
@SpringBootTest
@Slf4j
public class OSSTest {
    @Autowired
    FileStorageService fileStorageService;
    @Value("${file.oss.prefix}")
    String prefix;
    @Value("${file.oss.web-site}")
    String webSite;

    @Test
    public void upload() throws FileNotFoundException {
        log.info("_____ {}   {}", prefix, webSite);
        String store = fileStorageService.store(prefix, "mei_nv1.jpg", new FileInputStream("/Users/liuqi/Desktop/图片素材/WechatIMG9336.jpeg"));
        log.info("文件在 oss路径  {}", store);
        log.info("获取 oss的文件路径  {}", webSite + store);

    }

    @Test
    public void delete(){
//        fileStorageService.delete("https://frame-leadnews.oss-cn-shanghai.aliyuncs.com/0001.jpeg");
        fileStorageService.delete("material/2023/1/20230111/mei_nv1.jpg");
        log.info("删除oss文件成功");
    }
}
