package com.frame.admin.controller.v1;

import com.frame.admin.service.AdChannelService;
import com.frame.model.admin.dto.ChannelDTO;
import com.frame.model.admin.pojo.AdChannel;
import com.frame.model.common.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 *@ClassName AdChannelController
 *@Description TODO
 *@Author LiuQi
 *@Date 2022/12/20 11:01
 *@Version 1.0
 */
@RestController
@RequestMapping(value = "/api/v1/channel")
@Api(value = "频道信息 相关接口API")
public class AdChannelController {
    @Autowired
    private AdChannelService channelService;

    @ApiOperation(value = "根据条件分页查询频道信息列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseResult list(@RequestBody ChannelDTO channelDTO) {
        return channelService.findByNameAndPage(channelDTO);
    }

    @ApiOperation(value = "保存频道信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(@RequestBody AdChannel channel) {
        return channelService.insert(channel);
    }

    @ApiOperation(value = "更新频道信息")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody AdChannel channel) {
        return channelService.update(channel);
    }

    @ApiOperation(value = "删除频道信息")
    @RequestMapping(value = "/del/{channelId}", method = RequestMethod.DELETE)
    public ResponseResult delete(@PathVariable @ApiParam(value = "频道id") String channelId) {
        return channelService.deleteById(channelId);
    }

}
