package com.wqy.modules.shopping.controller;

import com.wqy.modules.common.pojo.*;
import com.wqy.modules.shopping.entity.ShGoods;
import com.wqy.modules.shopping.entity.ShUser;
import com.wqy.modules.shopping.service.IShGoodsService;
import com.wqy.modules.shopping.service.IShUserService;
import com.wqy.utils.UUIDUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;
import java.util.Map;

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
    @GetMapping("/search")
    public PageResult getGoodsPage(@RequestParam(value = "pagenum")String pagenum, @RequestParam(value = "pagesize")String pagesize,@RequestParam(value = "query")String query){


          return  new PageResult(Long.valueOf(iShUserService.selectAllBySearch(query).size()),iShUserService.getUserPage(Integer.valueOf( pagenum),Integer.valueOf(pagesize),query ));
    }

    /**
     * 查询全部
     * **/
    /*@GetMapping("/search/all")
    public Result getUserAll() {
        return new Result(true,StatusCode.OK,"成功",iShUserService.searchAll());
    }*/
    /**
     * 新增
     * **/
    @PostMapping("/add")
    public Result addShUser(@RequestBody ShUser shUser){
        shUser.setId(UUIDUtils.getUUID());//设置UUID
        iShUserService.addShUser(shUser);
        return new Result(true,StatusCode.OK,"成功");
    }
    /**
     * 修改
     * **/
    @PutMapping(value = "/update/{id}")
    public Result updataShUser(@RequestBody ShUser shUser,@PathVariable String id){
        shUser.setId(id);
        iShUserService.updateShUser(shUser);
        return new Result(true,StatusCode.OK,"成功");
    }
    /**
     * 删除
     * **/
    @DeleteMapping(value = "/delete/{id}")
    public Result deleteShUser(@PathVariable String id){
        iShUserService.deleteShUser(id);
        return new Result(true,StatusCode.OK,"成功");
    }
}
