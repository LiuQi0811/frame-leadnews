package com.frame.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frame.model.admin.dto.ChannelDTO;
import com.frame.model.admin.pojo.AdChannel;
import com.frame.model.common.ResponseResult;

/*
 *@ClassName AdChannelService
 *@Description 频道信息 逻辑层
 *@Author LiuQi
 *@Date 2022/12/19 23:07
 *@Version 1.0
 */
public interface AdChannelService extends IService<AdChannel> {
    /**
     * 根据名称分页 查询频道列表信息*
     *
     * @param channelDTO
     * @return
     */
    ResponseResult findByNameAndPage(ChannelDTO channelDTO);

    /**
     * 新增 频道信息 *
     *
     * @param channel
     * @return
     */
    ResponseResult insert(AdChannel channel);

    /**
     * 更新 频道信息 *
     *
     * @param channel
     * @return
     */
    ResponseResult update(AdChannel channel);

    /**
     * 删除 频道信息 *
     *
     * @param channelId
     * @return
     */
    ResponseResult deleteById(String channelId);


}
