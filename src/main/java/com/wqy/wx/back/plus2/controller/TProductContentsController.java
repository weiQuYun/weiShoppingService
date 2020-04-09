package com.wqy.wx.back.plus2.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus2.entity.TProductContents;
import com.wqy.wx.back.plus2.service.ITProductContentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author licm
 * @since 2020-04-03
 */
@Api(tags = "商品详情介绍表接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/productContents")
public class TProductContentsController {
    @Autowired
    private ITProductContentsService itProductContentsService;

    @GetMapping("/list/{id}")
    @ApiOperation("获取全部实际上只能获取单个商品的 获取全部没有意义")
    public TProductContents getProductContentsAll(@PathVariable String id) {
        return itProductContentsService.searchAll(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public Boolean deleteProductContents(@PathVariable String id) {
        return itProductContentsService.deleteProductContents(id);
    }

    @PostMapping("")
    @ApiOperation("添加")
    public Boolean addProductContents(@RequestBody TProductContents tProductContents) {
        return itProductContentsService.insertProductContents(tProductContents);
    }

    @PutMapping("")
    @ApiOperation("修改")
    public Boolean updateProductContents(@RequestBody TProductContents tProductContents) {
        return itProductContentsService.updateProductContents(tProductContents);
    }

    //以下不存在
//    @GetMapping("/page/{page}/{size}")
//    @ApiOperation("分页查询")
//    public void seachProductContentsPage(@PathVariable int page, @PathVariable int size) {
//        itProductContentsService.searchAll(page, size);
//    }
//    @PutMapping("/batch")
//    @ApiOperation("批量修改")
//    public void updateProductContentsBatch(@RequestBody List<TProductContents> list){
//        itProductContentsService.updateProductContents(list);
//    }
//    @PostMapping("/batch")
//    @ApiOperation("批量添加")
//    public void addProductContentsBatch(@RequestBody List<TProductContents> list){
//        itProductContentsService.insertProductContents(list);
//    }
//    @DeleteMapping("/delete/{id}")
//    @ApiOperation("批量删除")
//    public void deleteProductContentsBatch(@PathVariable List<String> id){
//        itProductContentsService.deleteProductContents(id);
//    }
}
