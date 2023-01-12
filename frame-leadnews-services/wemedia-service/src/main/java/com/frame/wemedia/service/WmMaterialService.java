package com.frame.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frame.model.common.ResponseResult;
import com.frame.model.wemedia.pojo.WmMaterial;
import org.springframework.web.multipart.MultipartFile;

/*
 *@ClassName WmMaterialService
 *@Description 自媒体图文信息 逻辑层
 *@Author LiuQi
 *@Date 2023/1/12 13:13
 *@Version 1.0
 */
public interface WmMaterialService  extends IService<WmMaterial> {
    /**
     * 上传图片素材
     * @param multipartFile
     * @return
     */
    ResponseResult<WmMaterial> uploadPicture(MultipartFile multipartFile);
}
