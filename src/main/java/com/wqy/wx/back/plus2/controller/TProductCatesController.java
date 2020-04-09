package com.wqy.wx.back.plus2.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus2.entity.TProductCates;
import com.wqy.wx.back.plus2.service.ITProductCatesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-03
 */
@Api(tags = "商品分类表接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/productCates")
public class TProductCatesController {
    @Autowired
    private ITProductCatesService itProductCatesService;

    @GetMapping("/list")
    @ApiOperation("获取全部")
    public List<TProductCates> getProductAll() {
        return itProductCatesService.searchAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public Boolean deleteProductCates(@PathVariable String id) {
        return itProductCatesService.deleteProductCates(id);
    }

    @PostMapping("")
    @ApiOperation("添加")
    public Boolean addProductCates(@RequestBody TProductCates tProductCates) {
        return itProductCatesService.insertProductCates(tProductCates);
    }

    @PutMapping("")
    @ApiOperation("修改")
    public Boolean updateProductCates(@RequestBody TProductCates tProductCates) {
        return itProductCatesService.updateProductCates(tProductCates);
    }

    @GetMapping("/page/{page}/{size}")
    @ApiOperation("分页查询")
    public Page<TProductCates> seachProductCatesPage(@PathVariable int page, @PathVariable int size) {
        return itProductCatesService.searchAll(page, size);
    }

    @PutMapping("/batch")
    @ApiOperation("批量修改")
    public Boolean updateProductCatesBatch(@RequestBody List<TProductCates> list) {
        itProductCatesService.updateProductCates(list);
        return true;
    }

    @PostMapping("/batch")
    @ApiOperation("批量添加")
    public Boolean addProductCatesBatch(@RequestBody List<TProductCates> list) {
        itProductCatesService.insertProductCates(list);
        return true;
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("批量删除")
    public Boolean deleteProductCatesBatch(@PathVariable List<String> id) {
        itProductCatesService.deleteProductCates(id);
        return true;
    }
}
