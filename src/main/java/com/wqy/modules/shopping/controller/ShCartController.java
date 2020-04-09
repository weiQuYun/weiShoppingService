package com.wqy.modules.shopping.controller;

import com.github.pagehelper.PageInfo;
import com.wqy.modules.common.pojo.Page;
import com.wqy.modules.common.pojo.Result;
import com.wqy.modules.common.pojo.StatusCode;
import com.wqy.modules.shopping.entity.ShCart;
import com.wqy.modules.shopping.entity.ShType;
import com.wqy.modules.shopping.service.IShCartService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-01
 */
@Api(tags = {"购物车"})
@RestController
@RequestMapping("/shCart")
public class ShCartController {
    @Autowired
    private IShCartService iShCartService;
    /*
     *  查询全部
     * */
    @PostMapping("findAll")
    public List<ShCart> findAll(@RequestBody ShCart shCart){
        return iShCartService.selectAll(shCart.getUserId());
    }

    @PostMapping("addCart")
    public Result addType(@RequestBody ShCart shCart){
        iShCartService.addCart(shCart);
        return new Result(true, StatusCode.OK,"成功");
    }
    /**
     * 根据用户id修改
     * **/
    @PutMapping(value = "/update/{userId}")
    public Result updateShUser(@RequestBody ShCart shCart, @PathVariable String userId){
        shCart.setUserId(userId);
        iShCartService.updateCart(shCart);
        return new Result(true,StatusCode.OK,"成功");
    }
    /**
     * 删除
     * **/
    @DeleteMapping(value = "/delete/{goodsId}/{userId}")
    public Result deleteShUser(@PathVariable String goodsId,String userId){
        iShCartService.deleteCart(goodsId, userId);
        return new Result(true,StatusCode.OK,"成功");
    }
}
