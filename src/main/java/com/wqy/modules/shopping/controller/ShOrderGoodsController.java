package com.wqy.modules.shopping.controller;

import com.wqy.modules.common.pojo.Result;
import com.wqy.modules.shopping.entity.ShOrder;
import com.wqy.modules.shopping.entity.ShOrderGoods;
import com.wqy.modules.shopping.service.IShOrderGoodsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @author licm
 * @since 2020-04-01
 */
@Api(tags = {""})
@RestController
@RequestMapping("/shOrderGoods")
public class ShOrderGoodsController {
    @Autowired
    private IShOrderGoodsService iShOrderGoodsService;



    ///这里有修改
    @GetMapping("/search/{id}")
    public Result getOrderGoods(@RequestParam String id) {
        ShOrderGoods shOrderGoods = new ShOrderGoods();
        shOrderGoods.setId("123");
        System.out.println(iShOrderGoodsService.getOrderGoods(shOrderGoods));
        return new Result();
    }
}
