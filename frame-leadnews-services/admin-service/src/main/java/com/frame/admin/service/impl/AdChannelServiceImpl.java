package com.frame.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.admin.mapper.AdChannelMapper;
import com.frame.admin.service.AdChannelService;
import com.frame.exception.common.CustomExceptionHandler;
import com.frame.model.admin.dto.ChannelDTO;
import com.frame.model.admin.pojo.AdChannel;
import com.frame.model.common.PageResponseResult;
import com.frame.model.common.ResponseResult;
import com.frame.model.common.enums.AppHttpCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/*
 *@ClassName AdChannelServiceImpl
 *@Description 频道信息 逻辑实现层
 *@Author LiuQi
 *@Date 2022/12/19 23:06
 *@Version 1.0
 */
@Service(value = "channelService")
public class AdChannelServiceImpl extends ServiceImpl<AdChannelMapper, AdChannel> implements AdChannelService {
    /**
     * 根据名称分页 查询频道列表信息*
     *
     * @param channelDTO
     * @return
     */
    @Override
    public ResponseResult findByNameAndPage(ChannelDTO channelDTO) {
        //1. 参数校验 非空判断 分页
        if (channelDTO == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "频道信息参数为空");
        }
        //分页逻辑判断
        channelDTO.checkParam();
        // 条件查询
        // 分页封装
        Page<AdChannel> pageReq = new Page<>(channelDTO.getPage(), channelDTO.getSize());
        // 封装条件参数 name status sort排序
        LambdaQueryWrapper<AdChannel> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(channelDTO.getName())) { //  频道名称不为空 模糊查询
            wrapper.like(AdChannel::getName, channelDTO.getName());
        }
        if (channelDTO.getStatus() != null) {//频道状态不为空 精确查询
            wrapper.eq(AdChannel::getStatus, channelDTO.getStatus());
        }
        //排序
        wrapper.orderByAsc(AdChannel::getSort);
        Page<AdChannel> pageResult = this.page(pageReq, wrapper);
        PageResponseResult pageResponseResult = PageResponseResult.builder()
                .currentPage(channelDTO.getPage())
                .size(channelDTO.getSize())
                .total(pageResult.getTotal())
                .data(pageReq.getRecords())
                .build();
        return pageResponseResult;
    }


    /**
     * 新增 频道信息 *
     *
     * @param channel
     * @return
     */
    @Override
    public ResponseResult insert(AdChannel channel) {
        //参数校验  频道名称 不能为空 长度不能大于10 不能重复
        String channelName = channel.getName();
        if (StringUtils.isBlank(channelName)) { //频道名称为空
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "频道名称不能为空");
        }
        if (channelName.length() > 10) { // 频道名称 长度大于10
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "频道名称长度不能大于10");

        }
        // 频道名称是否重复
        int count = this.count(Wrappers.<AdChannel>lambdaQuery().eq(AdChannel::getName, channelName));
        if (count > 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "频道名称不能重复");
        }
        channel.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        channel.setCreateTime(LocalDateTime.now());
        this.save(channel);
        //保存频道
        return ResponseResult.okResult();
    }

    /**
     * 更新 频道信息 *
     *
     * @param channel
     * @return
     */
    @Override
    public ResponseResult update(AdChannel channel) {
        //校验 id
        if (channel.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "频道id不能为空");
        }
        AdChannel oldChannel = this.getById(channel.getId());
        if (oldChannel == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "频道不存在");
        }
        // 频道名称不能为空 并且 修改频道名称 查询 数据库里面是否有重复的频道名称
        String channelName = channel.getName();
        if (StringUtils.isNotBlank(channelName) && !channelName.equals(oldChannel.getName())) {
            // 频道名称是否重复 校验
            int count = this.count(Wrappers.<AdChannel>lambdaQuery().eq(AdChannel::getName, channelName));
            if (count > 0) {
                return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "频道名称不能重复");
            }
        }
        // 修改频道信息
        this.updateById(channel);

        return ResponseResult.okResult();
    }


    /**
     * 删除 频道信息 *
     *
     * @param channelId
     * @return
     */
    @Override
    public ResponseResult deleteById(String channelId) {

        if(Integer.parseInt(channelId) > 40){
            throw new CustomExceptionHandler(AppHttpCodeEnum.PARAM_INVALID,"业务参数校验失败，频道id 大于了40");
        }


        // 校验 id
        if (channelId == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "频道id不能为空");
        }
        AdChannel channel = this.getById(channelId);
        if (channel == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "频道不存在");
        }
        if (channel.getStatus()) {// 频道状态 为true 不能删除

            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_ALLOW, "频道有效，不允许删除");

        }
        //删除频道
        this.removeById(channelId);
        return ResponseResult.okResult();
    }
}
