package com.wqy.modules.shopping.controller;

import com.wqy.modules.common.pojo.Result;
import com.wqy.modules.common.pojo.StatusCode;
import com.wqy.modules.shopping.entity.ShCart;
import com.wqy.modules.shopping.entity.ShOrder;
import com.wqy.modules.shopping.service.IShOrderService;
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
@RequestMapping("/shOrder")
public class ShOrderController {
    @Autowired
    private IShOrderService iShOrderService;
    /*
     *  查询全部
     * */
    @PostMapping("/CreateOrder")
    public ShOrder findAll(@RequestBody List<ShCart> shCarts){
        try {
            ShOrder shOrder = iShOrderService.selectAll(shCarts);
            return shOrder;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/updateOrder")
    public Result addType(@RequestBody ShOrder shOrder){
        try {
            if (iShOrderService.updateByOrderId(shOrder)) {
                return new Result(true, StatusCode.OK,"成功");
            } else {
                return new Result(false, StatusCode.ERROR,"失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      return new Result(false, StatusCode.ERROR,"失败");
    }

}
