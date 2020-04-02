package com.wqy.modules.shopping.controller;

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
@Api(tags = {""})
@RestController
@RequestMapping("/login/shGoods")
public class ShGoodsController {
    @Autowired
    private IShGoodsService iShGoodsService;
    /**
     * 分页查询
     * **/
    @GetMapping("/search/{page}/{size}")
    public Result getGoodsPage(@PathVariable int page, @PathVariable  int size){
        Page<ShGoods> goodsPage = iShGoodsService.getGoodsPage(page, size);
        PageResult<ShGoods> pageResult = new PageResult<ShGoods>(goodsPage.getTotal(),goodsPage.getList());
        return new Result(true, StatusCode.OK,"成功",pageResult);
    }

    /**
     * 查询全部
     * **/
    @GetMapping("/search")
    public Result getGoodsAll() {
        return new Result(true,StatusCode.OK,"成功",iShGoodsService.searchAll());
    }
    /**
     * 新增
     * **/
    @PostMapping()
    public Result addShGoods(@RequestParam ShGoods shGoods){
        shGoods.setId(UUIDUtils.getCharAndNumr(21));//设置UUID
        iShGoodsService.addShGoods(shGoods);
        return new Result(true,StatusCode.OK,"成功");
    }
    /**
     * 修改
     * **/
    @PutMapping(value = "/{id}")
    public Result updataShGoosa(@RequestBody ShGoods shGoods,@PathVariable String id){
        shGoods.setId(id);
        iShGoodsService.updateShGoods(shGoods);
        return new Result(true,StatusCode.OK,"成功");
    }
    /**
     * 删除
     * **/
    @DeleteMapping("/{id}")
    public Result deleteShGoods(@PathVariable String id){
        iShGoodsService.deleteShGoods(id);
        return new Result(true,StatusCode.OK,"成功");
    }


}
