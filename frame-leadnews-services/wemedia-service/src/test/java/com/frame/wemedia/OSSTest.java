package com.frame.wemedia;

import com.frame.file.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

/*
 *@ClassName OSSTest
 *@Description TODO
 *@Author LiuQi
 *@Date 2023/1/11 10:45
 *@Version 1.0
 */
@SpringBootTest
@Slf4j
public class OSSTest
{
    @Autowired
    FileStorageService fileStorageService;
    @Value("${file.oss.prefix}")
    String prefix;
    @Value("${file.oss.web-site}")
    String webSite;
    @Test
    public void upload(){
        log.info("_____ {}   {}",prefix,webSite);
    }
}
