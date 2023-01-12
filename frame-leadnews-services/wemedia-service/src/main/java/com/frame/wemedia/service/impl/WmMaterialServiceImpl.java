package com.frame.wemedia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.model.wemedia.pojo.WmMaterial;
import com.frame.wemedia.mapper.WmMaterialMapper;
import com.frame.wemedia.service.WmMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
