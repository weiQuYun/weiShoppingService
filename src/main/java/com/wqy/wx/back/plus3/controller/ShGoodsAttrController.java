package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus3.entity.ShGoods;
import com.wqy.wx.back.plus3.entity.ShGoodsAttr;
import com.wqy.wx.back.plus3.service.IShGoodsAttrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-09
 */
@Api(tags = "商品详情表(手机颜色) 已完成")
@RestController
@RequestMapping(Constant.MAPPING + "/shGoodsAttr")
public class ShGoodsAttrController {
    @Autowired
    private IShGoodsAttrService iShGoodsAttrService;

    @GetMapping("/list")
    @ApiOperation(value = "获取改商品详细类型就是手机颜色不同 只要商品ID ")
    public List<ShGoodsAttr> getShGoodsAttrAll(ShGoods shGoods) {
        return iShGoodsAttrService.selectAll(shGoods);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除商品详细类型，发送商品ID ")
    public String deleteGoodsAttr(@PathVariable String id) {
        if (iShGoodsAttrService.deleteGoodsAttr(id)) return "删除成功";
        return "删除失败ID错误";
    }

    @PostMapping("")
    @ApiOperation(value = "添加商品类型就是红色白色手机，需要商品ID")
    public String insertShGoods(@RequestBody ShGoodsAttr shGoodsAttr) {
        if (shGoodsAttr.getGoodsId().equals("")) {
            return "添加失败,必须要有GoodsId";
        } else if (iShGoodsAttrService.insertShGoodsAttr(shGoodsAttr)) return "添加成功";
        return "添加失败";
    }

    @PutMapping("")
    @ApiOperation(value = "修改商品类型 必须要有双ID！")
    public String updateShGoods(@RequestBody ShGoodsAttr shGoodsAttr) {
        if (shGoodsAttr.getGoodsId().equals("")) {
            return "修改失败,必须要有GoodsId";
        } else if (shGoodsAttr.getId().equals("")) {
            return "修改失败,必须要有类型ID";
        }else if (iShGoodsAttrService.updateShGoodsAttr(shGoodsAttr)) return "修改成功";
        return "修改失败，不知道为什么";
    }

    //这个东西不存在分页查询 你只能查全部
//    @GetMapping("/page")
//    @ApiOperation(value = "分页查询")
//    public Page<ShGoods> getPageShGoods(ShGoods shGoods, PageDTO pageDTO) {
//        return iShGoodsAttrService.getPageShGoods(shGoods, pageDTO);
//    }

    @PutMapping("/batch")
    @ApiOperation(value = "批量修改")
    public String updateShGoods(@RequestBody List<ShGoodsAttr> list) {
        if (iShGoodsAttrService.updateShGoodsAttr(list)) return "修改成功";
        return "修改失败";
    }

    @PostMapping("/batch")
    @ApiOperation(value = "批量新增")
    public String insertShGoods(@RequestBody List<ShGoodsAttr> list) {
        if (iShGoodsAttrService.insertShGoodsAttr(list)) return "添加成功";
        return "添加失败";
    }

    @DeleteMapping("/batch")
    @ApiOperation(value = "批量删除")
    public String deleteGoods(@RequestBody List<String> list) {
        if (iShGoodsAttrService.deleteGoodsAttr(list)) return "删除成功";
        return "删除失败ID错误";
    }
}
