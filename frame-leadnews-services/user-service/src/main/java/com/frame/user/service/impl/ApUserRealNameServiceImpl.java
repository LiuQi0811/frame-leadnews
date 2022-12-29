package com.frame.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frame.common.constants.admin.AdminConstants;
import com.frame.exception.common.CustomExceptionHandler;
import com.frame.feign.client.ArticleFeignClient;
import com.frame.feign.client.WeMediaFeignClient;
import com.frame.model.article.pojo.ApAuthor;
import com.frame.model.common.PageResponseResult;
import com.frame.model.common.ResponseResult;
import com.frame.model.common.enums.AppHttpCodeEnum;
import com.frame.model.user.dto.AuthDTO;
import com.frame.model.user.pojo.ApUser;
import com.frame.model.user.pojo.ApUserRealName;
import com.frame.model.wemedia.pojo.WmUser;
import com.frame.user.mapper.ApUserMapper;
import com.frame.user.mapper.ApUserRealNameMapper;
import com.frame.user.service.ApUserRealNameService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/*
 *@ClassName ApUserRealNameServiceImpl
 *@Description 用户实名认证 逻辑实现层
 *@Author LiuQi
 *@Date 2022/12/24 18:49
 *@Version 1.0
 */
@Service
public class ApUserRealNameServiceImpl extends ServiceImpl<ApUserRealNameMapper, ApUserRealName> implements ApUserRealNameService {
    @Autowired
    private ApUserMapper apUserMapper;

    /**
     * 根据状态查询需要认证相关的用户信息
     *
     * @param authDTO
     * @return
     */
    @Override
    public ResponseResult loadListByStatus(AuthDTO authDTO) {
        //校验参数 分页
        if (authDTO == null) {
            throw new CustomExceptionHandler(AppHttpCodeEnum.PARAM_INVALID, "");
        }
        authDTO.checkParam();
        //封装查询条件
        Page<ApUserRealName> pageReq = new Page<>(authDTO.getPage(), authDTO.getSize());
        LambdaQueryWrapper<ApUserRealName> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(authDTO.getStatus() != null, ApUserRealName::getStatus, authDTO.getStatus());
        //执行查询 封装查询条件
        Page<ApUserRealName> pageResult = this.page(pageReq, queryWrapper);

        return new PageResponseResult(authDTO.getPage(), authDTO.getSize(), pageResult.getTotal(), pageResult.getRecords());
    }

    /**
     * 根据状态进行审核
     *
     * @param authDTO
     * @param status  2 审核失败 9审核成功
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult updateStatusById(AuthDTO authDTO, String status) {
        //验证参数 实名认证id不能为空
        if (authDTO.getId() == null) {
            throw new CustomExceptionHandler(AppHttpCodeEnum.PARAM_INVALID, "实名认证id不能为空");
        }
        //根据实名认证的id 查询 ap_user_realname数据           ap_user_realname(userId) 用户认证信息
        ApUserRealName apUserRealName = this.getById(authDTO.getId()); //获取实名认证信息对象
        // 判断实名认证的状态是否是待审核 （1 待审核 2 失败 9 通过）
        if (!apUserRealName.getStatus().equals(AdminConstants.WAIT_AUTH)) {
            throw new CustomExceptionHandler(AppHttpCodeEnum.DATA_NOT_ALLOW, "实名认证的状态不是待审核状态");
        }
        // 根据实名认证信息关联的apUserId 查询 apUser信息   ap_user 用户信息
        ApUser apUser = apUserMapper.selectById(apUserRealName.getUserId());
        if (apUser == null) {
            throw new CustomExceptionHandler(AppHttpCodeEnum.DATA_NOT_EXIST, "实名认证关联的用户信息不存在");
        }
        // 修改实名 认证的状态
        apUserRealName.setStatus(status);
        if (StringUtils.isNotBlank(authDTO.getMsg())) { //驳回原因
            apUserRealName.setReason(authDTO.getMsg()); //赋值驳回原因
        }
        this.updateById(apUserRealName); //修改数据
        // 判断 状态是2失败（结束代码） 9通过（继续执行）
        if (status.equals(AdminConstants.FAIL_AUTH)) { //审核失败 未通过
            return ResponseResult.okResult();
        }
        // 开通自媒体账户 查询是否已开通  保存自媒体账户信息  wm_user 自媒体用户信息
        WmUser wmUser = createWmUser(apUser);
        // 创建文章作者信息 是否已经创建 保存作者信息     ap_author 文章作者信息
        createApAuthor(apUser, wmUser);

        return null;
    }

    @Autowired
    private ArticleFeignClient articleFeignClient;

    /**
     * 创建文章作者信息
     *
     * @param apUser
     * @param wmUser
     */
    private void createApAuthor(ApUser apUser, WmUser wmUser) {
        //根基用户id 查询作者细信息
        ResponseResult<ApAuthor> result = articleFeignClient.findByUserId(apUser.getId());
        if (!result.checkCode()) {
            //远程调用失败
            throw new CustomExceptionHandler(AppHttpCodeEnum.REMOTE_SERVER_ERROR,result.getErrorMessage());
        }
        //判断 作者信息是否存在
        ApAuthor apAuthor = result.getData(); //获取文章作者信息
        if (apAuthor!=null) {
            throw new CustomExceptionHandler(AppHttpCodeEnum.DATA_EXIST,"文章作者信息已存在");
        }
        //创建文章作者信息  保存
        apAuthor = ApAuthor.builder()
                .id(UUID.randomUUID().toString().replaceAll("-",""))
                .type("2")
                .userId(apUser.getId())// 用户id
                .WmUserId(wmUser.getId()) //自媒体用户id
                .createTime(LocalDateTime.now())
                .build();
        ResponseResult saveResult = articleFeignClient.save(apAuthor);
        if (!saveResult.checkCode()){
            //远程调用失败
            throw new CustomExceptionHandler(AppHttpCodeEnum.REMOTE_SERVER_ERROR, saveResult.getErrorMessage());
        }
    }


    @Autowired
    private WeMediaFeignClient weMediaFeignClient;

    /**
     * 创建自媒体用户 开通自媒体用户信息
     *
     * @param apUser
     * @return
     */
    private WmUser createWmUser(ApUser apUser) {
        // 调用自媒体feign 根据用户名 查询自媒体用户信息
        ResponseResult<WmUser> result = weMediaFeignClient.findByName(apUser.getName());
        if (!result.checkCode()) {
            //远程调用失败
            throw new CustomExceptionHandler(AppHttpCodeEnum.REMOTE_SERVER_ERROR, result.getErrorMessage());
        }
        WmUser wmUser = result.getData(); //获取用户信息
        // 判断是否存在
        if (wmUser != null) {
            throw new CustomExceptionHandler(AppHttpCodeEnum.DATA_EXIST, "自媒体账户已存在");
        }

        // 基于apUser用户信息 赋值 wmUser
        wmUser = WmUser.builder()
                .id(UUID.randomUUID().toString().replaceAll("-", ""))
                .apUserId(apUser.getId())
                .name(apUser.getName())
                .password(apUser.getPassword())
                .salt(apUser.getSalt())
                .image(apUser.getImage())
                .phone(apUser.getPhone())
                .status(" 9")
                .type("0")
                .createTime(LocalDateTime.now())
                .build();

        // 调用自媒体feign 保存自媒体用户信息
        ResponseResult<WmUser> saveResult = weMediaFeignClient.save(wmUser);
        if (!saveResult.checkCode()) {
            //远程调用失败
            throw new CustomExceptionHandler(AppHttpCodeEnum.REMOTE_SERVER_ERROR, saveResult.getErrorMessage());
        }
        return saveResult.getData();
    }
}
