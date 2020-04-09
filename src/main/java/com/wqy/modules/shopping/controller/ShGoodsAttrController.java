package com.wqy.modules.shopping.controller;

import com.github.pagehelper.PageInfo;
import com.wqy.modules.common.pojo.Page;
import com.wqy.modules.common.pojo.Result;
import com.wqy.modules.common.pojo.StatusCode;
import com.wqy.modules.shopping.entity.ShGoods;
import com.wqy.modules.shopping.entity.ShGoodsAttr;
import com.wqy.modules.shopping.service.IShGoodsAttrService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author licm
 * @since 2020-04-01
 */
@Api(tags = {"商品属性"})
@RestController
@RequestMapping("/shGoodsAttr")
public class ShGoodsAttrController {
    @Autowired
    private IShGoodsAttrService shGoodsAttrService;
    /**
     * 分页查询
     * **/
    @PostMapping("/findAll")
    public Result selectAttr(@RequestBody Page<ShGoods> page){
        PageInfo<ShGoods> shGoodsPageInfo = shGoodsAttrService.selectAll(page);
        return new Result(true, StatusCode.OK,"成功",shGoodsPageInfo);
    }


    /**
     * 新增
     * **/
    @PostMapping("/add")
    public Result addAttr(@RequestBody ShGoodsAttr shGoodsAttr){
        //shGoods.setId(UUIDUtils.getCharAndNumr(21));//设置UUID
        shGoodsAttrService.addGoodsAttr(shGoodsAttr);
        return new Result(true,StatusCode.OK,"成功");
    }
    /**
     * 修改
     * **/
    @PutMapping(value = "/update")
    public Result updateAttr(@RequestBody ShGoodsAttr shGoodsAttr){
        shGoodsAttrService.updateGoodsAttr(shGoodsAttr);
        return new Result(true,StatusCode.OK,"成功");
    }
    /**
     * 删除
     * **/
    @DeleteMapping("delete/{id}")
    public Result deleteAttr(@PathVariable String id){
        shGoodsAttrService.deleteGoodsAttr(id);
        return new Result(true,StatusCode.OK,"成功");
    }


}
