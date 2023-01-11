package com.frame.wemedia.controller.v1;

import com.frame.model.common.ResponseResult;
import com.frame.model.wemedia.dto.WmUserDTO;
import com.frame.wemedia.service.WmUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *@ClassName LoginController
 *@Description 自媒体用户 登录相关接口
 *@Author LiuQi
 *@Date 2023/1/11 20:26
 *@Version 1.0
 */
@RestController
@RequestMapping(value = "/login")
@Api(value = "自媒体用户 登录相关接口")
public class LoginController {
    @Autowired
    private WmUserService wmUserService;

    @PostMapping(value = "/in")
    public ResponseResult login(@RequestBody WmUserDTO wmUserDTO){
        return wmUserService.login(wmUserDTO);
    }
}
