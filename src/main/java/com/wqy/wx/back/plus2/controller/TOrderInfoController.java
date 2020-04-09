package com.wqy.wx.back.plus2.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus2.entity.TOrderInfo;
import com.wqy.wx.back.plus2.service.ITOrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-03
 */
@Api(tags = "成功订单页面接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/orderInfo")
public class TOrderInfoController {
    @Autowired
    private ITOrderInfoService itOrderInfoService;

    @GetMapping("/list")
    @ApiOperation("条件获取数据,需要通过店ID查询")//此处应该只发送产品ID 返回结果为
    public List<TOrderInfo> getList(TOrderInfo tOrderInfo) {
        return itOrderInfoService.getList(tOrderInfo);
    }

    @GetMapping("/page")
    @ApiOperation("条件获取分页数据")
    public Page<TOrderInfo> getPage(TOrderInfo tOrderInfo, PageDTO pageDTO) {
        return itOrderInfoService.getPage(tOrderInfo, pageDTO);
    }

    @PostMapping("")
    @ApiOperation("保存数据")
    public boolean save(@RequestBody TOrderInfo tOrderInfo) {
        return itOrderInfoService.save(tOrderInfo);
    }

    @PostMapping("/batch")
    @ApiOperation("批量保存")
    public boolean saveBatch(@RequestBody List<TOrderInfo> tOrderInfos) {
        return itOrderInfoService.saveBatch(tOrderInfos);
    }

    @PutMapping("")
    @ApiOperation("修改")
    public boolean update(@RequestBody TOrderInfo tOrderInfos) {
        return itOrderInfoService.updateById(tOrderInfos);
    }

    @PutMapping("/batch")
    @ApiOperation("批量修改")
    public boolean updateBatch(@RequestBody List<TOrderInfo> tOrderInfos) {
        return itOrderInfoService.updateBatchById(tOrderInfos);
    }

    @DeleteMapping
    @ApiOperation("删除")
    public boolean delete(@RequestBody TOrderInfo tOrderInfos) {
        return itOrderInfoService.removeById(tOrderInfos);
    }

    @DeleteMapping("/batch")
    @ApiOperation("批量删除")
    public boolean deleteBatch(@RequestBody List<TOrderInfo> tOrderInfos) {
        return itOrderInfoService.removeByIds(tOrderInfos);
    }
}
