package com.wqy.wx.back.plus3.controller;


import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.dto.Result;
import com.wqy.wx.back.dto.StatusCode;
import com.wqy.wx.back.dto.TemporaryMember;
import com.wqy.wx.back.plus3.entity.ShGoods;
import com.wqy.wx.back.plus3.entity.ShIndex;
import com.wqy.wx.back.plus3.service.IShIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licx
 * @since 2020-04-09
 */
@Api(tags = "主页公告栏 滚动条 已完成")
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
    @ApiOperation("首页公告获取，不接受任何参数返回所有公告 ID最大为最新")
    public Result<ShIndex> getNotice() {
        List<ShIndex> notice = iShIndexService.getNotice();
        return new Result<ShIndex>(true, StatusCode.OK, "查询成功", notice);
    }

    /**
     * 修改新增首页公告
     **/
    @PostMapping("")
    @PutMapping("")
    @ApiOperation("修改新增首页公告,不要传ID")
    public Result<ShIndex> updateIndex(@RequestBody ShIndex shIndex) {
        shIndex.setId(0);//就算传了ID 也给他改成0 不然传ID
        return new Result<ShIndex>(iShIndexService.save(shIndex), StatusCode.OK, "新增修改完成");
    }

    /**
     * 获取滚动条用于首页滚动条
     **/
    @GetMapping("/scroll")
    @ApiOperation("首页滚动条，我也不知道拿来干嘛，请求获取最新会员推荐人记录")
    public List<TemporaryMember> getScroll() {
        List<TemporaryMember> list = iShIndexService.getTemporaryMember();
        return list;
    }

    @GetMapping("/Goods")
    @ApiOperation(value = "主页商品展示")
    public List<ShGoods> getIndexShGoods() {
        return iShIndexService.getIndexShGoods();
    }


}
