package com.wqy.wx.back.plus2.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus2.entity.TProductImage;
import com.wqy.wx.back.plus2.service.ITProductImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-03
 */
@Api(tags = "商品图片more接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/productImage")
public class TProductImageController {
    @Autowired
    private ITProductImageService itProductImageService;

    /**
     * 测试通过 商品所有图片查询图片查询
     * 接受ID 为商品ID 不是实际ID
     **/
    @GetMapping("/list/{id}")
    @ApiOperation("获取全部")
    public List<TProductImage> getProductImageAll(@PathVariable String id) {
        return itProductImageService.searchAll(id);
    }

    /**
     * 测试通过 删除单张图片
     * 接受ID 为实际id
     **/
    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public Boolean deleteProductImage(@PathVariable String id) {
        return itProductImageService.deleteProductImage(id);
    }

    /**
     * 测试通过 添加图片
     * 其接受的类ID无意义
     **/
    @PostMapping("")
    @ApiOperation("添加")
    public Boolean addProductImage(@RequestBody TProductImage tProductImage) {
        return itProductImageService.insertProductImage(tProductImage);
    }

    /**
     * 测试通过
     **/
    @PutMapping("")
    @ApiOperation("修改")
    public Boolean updateProductImage(@RequestBody TProductImage tProductImage) {
        itProductImageService.updateProductImage(tProductImage);
        return true;
    }

    //此方法不存在
//    @GetMapping("/page/{page}/{size}")
//    @ApiOperation("分页查询")
//    public void searchProductImagePage(@PathVariable int page, @PathVariable int size) {
//        itProductImageService.searchAll(page, size);
//    }

    /**
     * 测试通过
     **/
    @PutMapping("/batch")
    @ApiOperation("批量修改")
    public Boolean updateProductImageBatch(@RequestBody List<TProductImage> list) {
        itProductImageService.updateProductImage(list);
        return true;
    }

    /**
     * 测试通过
     **/
    @PostMapping("/batch")
    @ApiOperation("批量添加,禁止传id")
    public Boolean addProductImageBatch(@RequestBody List<TProductImage> list) {
        itProductImageService.insertProductImage(list);
        return true;
    }

    /**
     * 测试通过
     **/
    @DeleteMapping("/delete/{id}")
    @ApiOperation("批量删除")
    public Boolean deleteProductImageBatch(@PathVariable List<String> id) {
        itProductImageService.deleteProductImage(id);
        return true;
    }
}
