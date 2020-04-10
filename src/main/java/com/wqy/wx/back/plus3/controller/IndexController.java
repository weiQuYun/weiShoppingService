package com.wqy.wx.back.plus3.controller;


import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.dto.Result;
import com.wqy.wx.back.dto.StatusCode;
import com.wqy.wx.back.plus3.entity.ShIndex;
import com.wqy.wx.back.plus3.service.IShIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author licx
 * @since 2020-04-09
 */
@Api(tags = "主页公告栏 滚动条")
@RestController
@RequestMapping(Constant.MAPPING + "/index")
public class IndexController {
    /**
     * 首页公告表controller
     * 用于首页公告栏及其滚动条实现
     **/

    @Autowired
    private IShIndexService iShIndexService; //主页服务

    /**
     * 获取首页公告
     **/
    @GetMapping("/list")
    @ApiOperation("首页公告获取，不接受任何参数返回最新首页公告")
    public Result<ShIndex> getNotice() {
        ShIndex notice = iShIndexService.getNotice();
        return new Result<ShIndex>(true, StatusCode.OK, "查询成功", notice);
    }

    @PostMapping("")
    @PutMapping("")
    @ApiOperation("修改新增首页公告")
    public Result<ShIndex> updateIndex(@RequestBody ShIndex shIndex) {
        return new Result<ShIndex>(iShIndexService.save(shIndex), StatusCode.OK, "新增修改完成");
    }


}
