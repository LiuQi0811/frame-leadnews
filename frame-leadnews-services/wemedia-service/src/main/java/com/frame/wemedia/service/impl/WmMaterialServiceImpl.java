package com.frame.wemedia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.exception.common.CustomExceptionHandler;
import com.frame.file.service.FileStorageService;
import com.frame.model.common.ResponseResult;
import com.frame.model.common.enums.AppHttpCodeEnum;
import com.frame.model.threadlocal.WmThreadLocalUtils;
import com.frame.model.wemedia.pojo.WmMaterial;
import com.frame.model.wemedia.pojo.WmUser;
import com.frame.wemedia.mapper.WmMaterialMapper;
import com.frame.wemedia.service.WmMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/*
 *@ClassName WmMaterialServiceImpl
 *@Description 自媒体图文信息 逻辑实现层
 *@Author LiuQi
 *@Date 2023/1/12 13:14
 *@Version 1.0
 */
@Service
@Slf4j
public class WmMaterialServiceImpl extends ServiceImpl<WmMaterialMapper, WmMaterial> implements WmMaterialService {

    @Autowired
    private FileStorageService fileStorageService;
    @Value("${file.oss.prefix}")
    private String prefix;

    @Value("${file.oss.web-site}")
    String webSite;
    /**
     * 上传图片素材
     * @param multipartFile
     * @return
     */
    @Override
    public ResponseResult<WmMaterial> uploadPicture(MultipartFile multipartFile) {
        // 参数校验（文件对象   判断是否登录  文件后缀是否支持）
        if (multipartFile==null||multipartFile.getSize()<=0){
        throw new CustomExceptionHandler(AppHttpCodeEnum.PARAM_INVALID,"请上传正确的文件");
        }

        WmUser user = WmThreadLocalUtils.getUser();
        if (user==null) { //判断是否登录
            throw new CustomExceptionHandler(AppHttpCodeEnum.NEED_LOGIN);
        }
        String originalFilename = multipartFile.getOriginalFilename(); //获取原始的文件名称 支持 [jpg jpeg png gif]
        if (!checkFileSuffix(originalFilename)) {// 文件后缀是否支持
            throw new CustomExceptionHandler(AppHttpCodeEnum.PARAM_INVALID,"文件类型不支持，目前支持：[jpg jpeg png gif]");
        }
        // 上传 oss（生成新的文件名称 上传到oss）
        String ossFilePath =null;
        try {
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "");
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));// 文件后缀名截取
            String fileName = newFileName + suffix;// 生成新的文件名称
             ossFilePath = fileStorageService.store(prefix,fileName,multipartFile.getInputStream()); // oss 文件路径
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomExceptionHandler(AppHttpCodeEnum.SERVER_ERROR,"上传文件到阿里云oss失败");
        }

        WmMaterial wmMaterial = WmMaterial.builder()
                .id(UUID.randomUUID().toString().replaceAll("-", ""))
                .userId(user.getId())
                .url(ossFilePath)
                .type("0")
                .isCollection("0")
                .createTime(LocalDateTime.now())
                .build(); //创建自媒体图文信息对象
        this.save(wmMaterial); // 保存到数据库
        String url = webSite + wmMaterial.getUrl();// oss文件完整路径 用于前端回显
        wmMaterial.setUrl(url);
        return ResponseResult.okResult(wmMaterial);
    }


    /**
     * 检测 文件后缀名
     * @param originalFilename
     * @return
     */
    public boolean checkFileSuffix(String originalFilename)
    {
        if (StringUtils.isNotBlank(originalFilename)) {
            List<String> allowSuffix = Arrays.asList(".jpg", ".jpeg", ".png", ".gif"); // 允许支持 素材类型的列表
            for (String suffix : allowSuffix) {
                if (originalFilename.endsWith(suffix)) { // 文件名 后缀名结尾符合
                    return true;
                }
            }
        }
        return false;
    }
}
