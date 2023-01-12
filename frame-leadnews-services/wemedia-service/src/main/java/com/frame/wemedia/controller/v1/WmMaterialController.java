package com.frame.wemedia.controller.v1;

import com.frame.model.common.ResponseResult;
import com.frame.wemedia.service.WmMaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/*
 *@ClassName WmMaterialController
 *@Description 自媒体图文信息 相关接口API
 *@Author LiuQi
 *@Date 2022/12/25 13:03
 *@Version 1.0
 */
@RestController
@RequestMapping(value = "/api/v1/material")
@Api(value = "自媒体图文信息 相关接口API")
public class WmMaterialController {


    @Autowired
    private WmMaterialService wmMaterialService;

    /**
     * 上传图片素材
     *
     * @param multipartFile 需要上传的文件
     * @return
     */
    @RequestMapping(value = "/uploadPicture", method = RequestMethod.POST)
    @ApiOperation(value = "上传图片素材")
    public ResponseResult uploadPicture(@RequestParam MultipartFile multipartFile) {
        return wmMaterialService.uploadPicture(multipartFile);
    }



}
