package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus3.entity.ShGoods;
import com.wqy.wx.back.plus3.service.IShGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-09
 */
@Api(tags = "商品表接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/shGoods")
public class ShGoodsController {
    @Autowired
    private IShGoodsService iShGoodsService;

    @GetMapping("/list")
    @ApiOperation(value = "获取全部,通过名称查询 无名称默认查询所有")
    public List<ShGoods> getShGoodsAll(ShGoods shGoods){
        return iShGoodsService.selectAll(shGoods);
    }

    @Delete("/{id}")
    @ApiOperation(value = "删除商品，发送商品ID ")
    public String deleteGoods(@PathVariable String id){
        if (iShGoodsService.deleteGoods(id))return "删除成功";
        return "删除失败ID错误";
    }

    @PostMapping("")
    @ApiOperation(value = "添加商品，不要传入ID！分类ID一定要有")
    public String insertShGoods(@RequestBody ShGoods shGoods){
        if (iShGoodsService.insertShGoods(shGoods)) return "添加成功";
        return "添加失败";
    }
}
