package com.frame.user.controller.v1;

import com.frame.common.constants.admin.AdminConstants;
import com.frame.model.common.ResponseResult;
import com.frame.model.user.dto.AuthDTO;
import com.frame.model.user.pojo.ApUser;
import com.frame.user.service.ApUserRealNameService;
import com.frame.user.service.ApUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

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
    @Autowired
    private ApUserService apUserService;

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


    /**
     * 实名认证通过
     *
     * @param authDTO
     * @return
     */
    @RequestMapping(value = "/authPass", method = RequestMethod.POST)
    @ApiOperation(value = "实名认证通过")
    public ResponseResult authPass(@RequestBody AuthDTO authDTO) {
        return apUserRealNameService.updateStatusById(authDTO, AdminConstants.PASS_AUTH);
    }


    /**
     * 实名认证失败
     *
     * @param authDTO
     * @return
     */
    @RequestMapping(value = "/authFail", method = RequestMethod.POST)
    @ApiOperation(value = "实名认证失败")
    public ResponseResult authFail(@RequestBody AuthDTO authDTO) {
        return apUserRealNameService.updateStatusById(authDTO, AdminConstants.FAIL_AUTH);
    }


    /**
     * 保存用户信息 临时使用
     *
     * @param apUser
     * @return
     */
    @RequestMapping(value = "/saveTemp", method = RequestMethod.POST)
    @ApiOperation(value = "保存用户信息 临时使用")
    public ResponseResult saveTemp(@RequestBody ApUser apUser) {
        String salt = apUser.getSalt();
        String password = salt + apUser.getPassword();
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        apUser = ApUser.builder()
                .id(UUID.randomUUID().toString().replaceAll("-",""))
                .name(apUser.getName())
                .salt(salt)
                .password(md5DigestAsHex)
                .sex(apUser.getSex())
                .flag(apUser.getFlag())
                .phone(apUser.getPhone())
                .isCertification("0")
                .isIdentityAuthentication("0")
                .image(apUser.getImage())
                .status("0")
                .createTime(LocalDateTime.now())
                .build();
        boolean save = apUserService.save(apUser);
        return ResponseResult.okResult(save);
    }


}
