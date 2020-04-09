package com.wqy.modules.shopping.controller;

import com.github.pagehelper.PageInfo;
import com.wqy.modules.common.pojo.Page;
import com.wqy.modules.common.pojo.PageResult;
import com.wqy.modules.common.pojo.Result;
import com.wqy.modules.common.pojo.StatusCode;
import com.wqy.modules.shopping.entity.ShGoods;
import com.wqy.modules.shopping.mapper.ShGoodsMapper;
import com.wqy.modules.shopping.service.IShGoodsService;
import com.wqy.utils.UUIDUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-01
 */
@Api(tags = {"商品表"})
@RestController
@RequestMapping("/shGoods")
public class ShGoodsController {
    @Autowired
    private IShGoodsService iShGoodsService;
    /**
     * 分页查询
     * **/
    @PostMapping("/findAll")
    public Result getGoodsPage(@RequestBody ShGoods shGoods,Page<ShGoods> page){
        PageInfo<ShGoods> shGoodsPageInfo = iShGoodsService.selectAll(page, shGoods);
        return new Result(true, StatusCode.OK,"成功",shGoodsPageInfo);
    }


    /**
     * 新增
     * **/
    @PostMapping("/add")
    public Result addShGoods(@RequestBody ShGoods shGoods){
        //shGoods.setId(UUIDUtils.getCharAndNumr(21));//设置UUID
        iShGoodsService.addGoods(shGoods);
        return new Result(true,StatusCode.OK,"成功");
    }
    /**
     * 修改
     * **/
    @PutMapping(value = "/update")
    public Result updataShGoosa(@RequestBody ShGoods shGoods){
        iShGoodsService.updateGoods(shGoods);
        return new Result(true,StatusCode.OK,"成功");
    }
    /**
     * 删除
     * **/
    @DeleteMapping("delete/{id}")
    public Result deleteShGoods(@PathVariable String id){
        iShGoodsService.deleteGoods(id);
        return new Result(true,StatusCode.OK,"成功");
    }
    /**
     * 上架
     */
    @PutMapping(value = "/putaway/{id}")
    public Result putawayGoods(@RequestParam String id){
        iShGoodsService.putawayGoods(id);
        return new Result(true,StatusCode.OK,"成功");
    }
    @PutMapping(value = "/soldOut/{id}")
    public Result soldOutGoods(@RequestParam String id){
        iShGoodsService.soldOutGoods(id);
        return new Result(true,StatusCode.OK,"成功");
    }

}
