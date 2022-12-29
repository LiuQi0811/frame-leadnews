package com.frame.wemedia.controller.v1;

import com.frame.model.common.ResponseResult;
import com.frame.model.wemedia.pojo.WmUser;
import com.frame.wemedia.service.WmUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

/*
 *@ClassName WmUserController
 *@Description 自媒体用户 相关接口API
 *@Author LiuQi
 *@Date 2022/12/25 13:03
 *@Version 1.0
 */
@RestController
@RequestMapping(value = "/api/v1/user")
@Api(value = "自媒体用户 相关接口API")
public class WmUserController {


    @Autowired
    private WmUserService wmUserService;

    /**
     * 根据用户名查询自媒体用户
     *
     * @param name 用户名
     * @return
     */
    @RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户名查询自媒体用户")
    public ResponseResult findByName(@PathVariable(value = "name") String name) {
        return wmUserService.findWeMediaUserByName(name);
    }


    /**
     * 保存自媒体用户
     * @param wmUser
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存自媒体用户")
    public ResponseResult save(@RequestBody WmUser wmUser) {
        return wmUserService.insert(wmUser);
    }


}
