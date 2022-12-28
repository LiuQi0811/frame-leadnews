package com.frame.user.controller.v1;

import com.frame.model.common.ResponseResult;
import com.frame.model.user.dto.AuthDTO;
import com.frame.user.service.ApUserRealNameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 *@ClassName ApUserRealNameController
 *@Description 实名认证 相关接口AP
 *@Author LiuQi
 *@Date 2022/12/25 09:02
 *@Version 1.0
 */
@RestController
@RequestMapping(value = "/api/v1/auth")
@Api(value = "实名认证 相关接口API")
public class ApUserRealNameController {
    @Autowired
    private ApUserRealNameService apUserRealNameService;

    /**
     * 根据状态查询需要认证相关的用户信息
     *
     * @param authDTO
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "根据状态查询需要认证相关的用户信息")
    public ResponseResult list(@RequestBody AuthDTO authDTO) {
        return apUserRealNameService.loadListByStatus(authDTO);
    }
}
