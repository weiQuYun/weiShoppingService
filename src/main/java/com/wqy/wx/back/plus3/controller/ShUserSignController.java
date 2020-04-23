package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus3.entity.ShUserSign;
import com.wqy.wx.back.plus3.service.IShUserSignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-09
 */
@Api(tags = "签到表接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/userSign")
public class ShUserSignController {
    @Autowired
    private IShUserSignService shUserSignService;

    @GetMapping("/sign")
    @ApiOperation("签到")
    public Boolean signIn(String userId) {
        return shUserSignService.signIn(userId);
    }

    @GetMapping("/sign/list")
    @ApiOperation("签到记录")
    public List<ShUserSign> getList(String userId) {
        return shUserSignService.getList(userId);
    }

    @GetMapping("/sign/count")
    @ApiOperation("签到总数")
    public Integer getCount(String userId) {
        List<ShUserSign> list = shUserSignService.getList(userId);
        if(CollectionUtils.isEmpty(list)){
            return 0;
        }
        return list.size();
    }
}
