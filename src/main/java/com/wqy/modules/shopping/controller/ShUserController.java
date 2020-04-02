package com.wqy.modules.shopping.controller;

import com.wqy.modules.common.pojo.Page;
import com.wqy.modules.common.pojo.PageResult;
import com.wqy.modules.common.pojo.Result;
import com.wqy.modules.common.pojo.StatusCode;
import com.wqy.modules.shopping.entity.ShGoods;
import com.wqy.modules.shopping.entity.ShUser;
import com.wqy.modules.shopping.service.IShGoodsService;
import com.wqy.modules.shopping.service.IShUserService;
import com.wqy.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

/**
* @author licm
* @since 2020-04-01
*/
@Api(tags = {""})
@RestController
@RequestMapping("/shUser")
    public class ShUserController {

    @Autowired
    private IShUserService iShUserService;
    /**
     * 分页查询
     * **/
    @GetMapping("/search/{page}/{size}")
    public Result getGoodsPage(@PathVariable int page, @PathVariable  int size){
        Page<ShUser> goodsPage = iShUserService.getUserPage(page, size);
        PageResult<ShUser> pageResult = new PageResult<ShUser>(goodsPage.getTotal(),goodsPage.getList());
        return new Result(true, StatusCode.OK,"成功",pageResult);
    }

    /**
     * 查询全部
     * **/
    @GetMapping("/search")
    public Result getUserAll() {
        return new Result(true,StatusCode.OK,"成功",iShUserService.searchAll());
    }
    /**
     * 新增
     * **/
    @PostMapping()
    public Result addShUser(@RequestParam ShUser shUser){
        shUser.setId(UUIDUtils.getCharAndNumr(21));//设置UUID
        iShUserService.addShUser(shUser);
        return new Result(true,StatusCode.OK,"成功");
    }
    /**
     * 修改
     * **/
    @PutMapping(value = "/{id}")
    public Result updataShUser(@RequestBody ShUser shUser,@PathVariable String id){
        shUser.setId(id);
        iShUserService.updateShUser(shUser);
        return new Result(true,StatusCode.OK,"成功");
    }
    /**
     * 删除
     * **/
    @DeleteMapping("/{id}")
    public Result deleteShUser(@PathVariable String id){
        iShUserService.deleteShUser(id);
        return new Result(true,StatusCode.OK,"成功");
    }
}
