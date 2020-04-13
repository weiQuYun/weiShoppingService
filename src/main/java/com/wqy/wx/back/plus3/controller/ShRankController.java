package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus3.entity.ShRank;
import com.wqy.wx.back.plus3.service.IShRankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-10
 */
@Api(tags = "团队分值排行榜接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/rank")
public class ShRankController {
    @Autowired
    private IShRankService iShRankService;

    @GetMapping("/list")
    @ApiOperation("条件查询排行榜")
    public List<ShRank> getList(ShRank shRank) {
        return iShRankService.getList(shRank);
    }
}
