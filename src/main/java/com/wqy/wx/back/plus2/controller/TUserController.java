package com.wqy.wx.back.plus2.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus2.entity.TUser;
import com.wqy.wx.back.plus2.service.ITUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-03
 */
@Api(tags = "后台用户接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/user")
public class TUserController {
    @Autowired
    private ITUserService itUserService;

    /**
     * 接口测试通过
     * url:/api/wqy/user/search
     * return 所有用户数据
     **/
    @GetMapping("/search")
    @ApiOperation("获取全部")
    public List<TUser> getTUserAll() {
        return itUserService.searchAll();
    }

    /**
     * 接口测试通过
     * url：/api/wqy/user/{id}
     * return id用户数据
     **/
    @GetMapping("/{id}")
    public TUser getTUser(@PathVariable Integer id) {
        return itUserService.getById(id);
    }

    /**
     * 测试通过 删除ID 数据
     **/
    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public Boolean deleteTUser(@PathVariable String id) {
        return itUserService.deleteTUser(id);
    }

    /**
     * 测试通过 post接受TUser
     * url /api/wqy/user
     * 实现已注入UUID 传什么ID 不重要
     * 新增用户默认建立店铺
     **/
    @PostMapping("")
    @ApiOperation("添加")
    public Boolean addTUser(@RequestBody TUser tUser) {
        return itUserService.insertTUser(tUser);
    }

    /**
     * 测试通过 实际是通过ID修改 ID 值不可更改！！！
     * url：/api/way/user
     **/
    @PutMapping("")
    @ApiOperation("修改")
    public Boolean updateTUser(@RequestBody TUser tUser) {
        return itUserService.updateTUser(tUser);
    }

    /**
     * 测试通过
     **/
    @GetMapping("/page/{page}/{size}")
    @ApiOperation("分页查询")
    public Page<TUser> searchTUserPage(@PathVariable int page, @PathVariable int size, TUser tUser) {
        return itUserService.searchAll(page, size, tUser);
    }
    //以下不存在
//    @PutMapping("/batch")
//    @ApiOperation("批量修改")
//    public Boolean updateTUserBatch(@RequestBody List<TUser> list){
//        return itUserService.updateTUser(list);
//    }
//    @PostMapping("/batch")
//    @ApiOperation("批量添加")
//    public Boolean addTUserBatch(@RequestBody List<TUser> list){
//        return itUserService.insertTUser(list);
//    }
//    @DeleteMapping("/delete/{id}")
//    @ApiOperation("批量删除")
//    public Boolean deleteTUserBatch(@PathVariable List<String> id){
//        return itUserService.deleteTUser(id);
//    }
}
