package com.wqy.wx.back.plus2.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus2.entity.TCart;
import com.wqy.wx.back.plus2.entity.TMenber;
import com.wqy.wx.back.plus2.service.ITCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-03
 */
@Api(tags = "购物车接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/cart")
public class TCartController {

    @Autowired
    private ITCartService itCartService;

    /**
     * 测试成功
     **/
    @GetMapping("/list")
    @ApiOperation("条件获取数据")
    public List<TCart> getList(TMenber tMenber) {
        return itCartService.getList(tMenber);
    }

    //暂时想不到购物车分页情况不做分页 此方法未实现
//    @GetMapping("/page")
//    @ApiOperation("条件获取分页数据")
//    public Page<TCart> getPage(TCart tCart, PageDTO pageDTO) {
//        return itCartService.getPage( tCart,pageDTO);
//    }
    @PostMapping("")
    @ApiOperation("保存数据")
    public boolean save(@RequestBody TCart tCart) {
        return itCartService.insertTCart(tCart);
    }

    @PostMapping("/batch")
    @ApiOperation("批量保存")
    public boolean saveBatch(@RequestBody List<TCart> tCarts) {
        return itCartService.saveBatch(tCarts);
    }

    //每修改一次购物车都得发一个ajax
    @PutMapping("")
    @ApiOperation("修改")
    public boolean update(@RequestBody TCart tCarts) {
        return itCartService.updateById(tCarts);
    }

    //不存在批量修改
    @PutMapping("/batch")
    @ApiOperation("批量修改")
    public boolean updateBatch(@RequestBody List<TCart> tCarts) {
        return itCartService.updateBatchById(tCarts);
    }

    @DeleteMapping
    @ApiOperation("删除")
    public boolean delete(@RequestBody TCart tCarts) {
        return itCartService.removeById(tCarts);
    }

    @DeleteMapping("/batch")
    @ApiOperation("批量删除")
    public boolean deleteBatch(@RequestBody List<TCart> tCarts) {
        return itCartService.removeByIds(tCarts);
    }
}
