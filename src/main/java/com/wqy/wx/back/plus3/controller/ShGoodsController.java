package com.wqy.wx.back.plus3.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus3.entity.ShGoods;
import com.wqy.wx.back.plus3.entity.ShGoodsAttr;
import com.wqy.wx.back.plus3.service.IShGoodsAttrService;
import com.wqy.wx.back.plus3.service.IShGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-09
 */
@Api(tags = "商品表接口管理 已完成")
@RestController
@RequestMapping(Constant.MAPPING + "/goods")
public class ShGoodsController {
    @Autowired
    private IShGoodsService iShGoodsService;
    @Autowired
    private IShGoodsAttrService iShGoodsAttrService;

    @GetMapping("/list")
    @ApiOperation(value = "获取全部,通过名称查询 无名称默认查询所有")
    public List<ShGoods> getShGoodsAll(ShGoods shGoods) {
        List<ShGoods> list = iShGoodsService.selectAll(shGoods);
        if (shGoods.getId().length()<30){
            return list;
        }
        List<ShGoodsAttr> shGoodsAttrs = iShGoodsAttrService.selectAll(shGoods);
        list.get(0).setGoodsAttrList(shGoodsAttrs);
        return list;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除商品，发送商品ID ")
    public String deleteGoods(@PathVariable String id) {
        if (iShGoodsService.deleteGoods(id)) return "删除成功";
        return "删除失败ID错误";
    }

    @PostMapping("")
    @ApiOperation(value = "添加商品，不要传入ID！分类ID一定要有")
    public String insertShGoods(@RequestBody ShGoods shGoods) {
        if (iShGoodsService.insertShGoods(shGoods)) return "添加成功";
        return "添加失败";
    }

    @PutMapping("")
    @ApiOperation(value = "修改商品 必须要有ID！")
    public String updateShGoods(@RequestBody ShGoods shGoods) {
        if (iShGoodsService.updateShGoods(shGoods)) return "修改成功";
        return "修改失败";
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public Page<ShGoods> getPageShGoods(ShGoods shGoods, PageDTO pageDTO) {
        return iShGoodsService.getPageShGoods(shGoods, pageDTO);
    }

    @PutMapping("/batch")
    @ApiOperation(value = "批量修改")
    public String updateShGoods(@RequestBody List<ShGoods> list) {
        if (iShGoodsService.updateShGoods(list)) return "修改成功";
        return "修改失败";
    }

    @PostMapping("/batch")
    @ApiOperation(value = "批量新增")
    public String insertShGoods(@RequestBody List<ShGoods> list) {
        if (iShGoodsService.insertShGoods(list)) return "添加成功";
        return "添加失败";
    }

    @DeleteMapping("/batch")
    @ApiOperation(value = "批量删除")
    public String deleteGoods(@RequestBody List<String> list) {
        if (iShGoodsService.deleteGoods(list)) return "删除成功";
        return "删除失败ID错误";
    }
}
