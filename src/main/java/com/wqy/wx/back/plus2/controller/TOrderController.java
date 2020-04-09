package com.wqy.wx.back.plus2.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus2.entity.TCart;
import com.wqy.wx.back.plus2.entity.TOrder;
import com.wqy.wx.back.plus2.service.ITOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-03
 */
@Api(tags = "订单页面接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/order")
public class TOrderController {
    @Autowired
    private ITOrderService itOrderService;

    @GetMapping("/list")//两种情况查询 1.用户端只能查询自己 获取用户ID 后台获取使用info表
    @ApiOperation("条件获取数据")
    public List<TOrder> getList(TOrder tOrder) {
        return itOrderService.getList(tOrder);
    }

    @GetMapping("/page")
    @ApiOperation("条件获取分页数据")
    public Page<TOrder> getPage(TOrder tOrder, PageDTO pageDTO) {
        return itOrderService.getPage(tOrder, pageDTO);
    }

    //这是订单生成入口
    @PostMapping("")
    @ApiOperation("新建数据")
    public TOrder save(@RequestBody List<TCart> tCartList) throws Exception {
        return itOrderService.insertOrder(tCartList);
    }

    //    @PostMapping("/batch")
//    @ApiOperation("批量保存")
//    public boolean saveBatch(@RequestBody List<TOrder> tOrders) {
//        return itOrderService.saveBatch( tOrders);
//    }
    //正式生成订单使用修改
    @PutMapping("")
    @ApiOperation("修改")
    public boolean update(@RequestBody TOrder tOrders) throws Exception {
        return itOrderService.updateByOrderId(tOrders);
    }

    //以下暂时未实现 删除应该能用
    @PutMapping("/batch")
    @ApiOperation("批量修改")
    public boolean updateBatch(@RequestBody List<TOrder> tOrders) {
        return itOrderService.updateBatchById(tOrders);
    }

    @DeleteMapping
    @ApiOperation("删除")
    public boolean delete(@RequestBody TOrder tOrders) {
        return itOrderService.removeById(tOrders);
    }

    @DeleteMapping("/batch")
    @ApiOperation("批量删除")
    public boolean deleteBatch(@RequestBody List<TOrder> tOrders) {
        return itOrderService.removeByIds(tOrders);
    }
}
