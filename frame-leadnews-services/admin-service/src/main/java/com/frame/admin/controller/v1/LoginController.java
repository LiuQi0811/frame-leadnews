package com.frame.admin.controller.v1;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.frame.admin.service.AdUserService;
import com.frame.model.admin.dto.AdUserDTO;
import com.frame.model.common.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 *@ClassName LoginController
 *@Description 运营平台登录 API
 *@Author LiuQi
 *@Date 2022/12/24 10:08
 *@Version 1.0
 */
@RestController
@RequestMapping(value = "/login")
@Api(value = "运营平台登录 API", tags = "运营平台登录 API")
public class LoginController {
    @Autowired
    private AdUserService userService;

    @RequestMapping(value = "/in", method = RequestMethod.POST)
    @ApiOperation(value = "登录功能")
    public ResponseResult login(@RequestBody AdUserDTO userDTO) {
        return userService.login(userDTO);
    }
}
