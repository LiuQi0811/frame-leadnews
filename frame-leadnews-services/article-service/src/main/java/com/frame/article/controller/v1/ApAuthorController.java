package com.frame.article.controller.v1;

import com.frame.article.service.ApAuthorService;
import com.frame.model.article.pojo.ApAuthor;
import com.frame.model.common.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 *@ClassName ApAuthorController
 *@Description 文章作者信息 相关接口API
 *@Author LiuQi
 *@Date 2022/12/28 13:34
 *@Version 1.0
 */
@RestController
@RequestMapping(value = "/api/v1/author")
@Api(value = "文章作者信息 相关接口API")
public class ApAuthorController {

    @Autowired
    private ApAuthorService apAuthorService;

    /**
     * 根据 ApUser id查询作者信息
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/findByUserId/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户id查询作者信息")
    public ResponseResult findByUserId(@PathVariable(value = "userId") String userId) {
        return ResponseResult.okResult(apAuthorService.findByUserId(userId));
    }

    /**
     * 保存文章作者信息
     * @param apAuthor
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存文章作者信息")
    public ResponseResult save(@RequestBody ApAuthor apAuthor) {
        return ResponseResult.okResult(apAuthorService.insert(apAuthor));
    }

}
