package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.plus3.entity.ShUser;
import com.wqy.wx.back.plus3.mapper.ShUserMapper;
import com.wqy.wx.back.plus3.service.IShUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-09
 */
@Api(tags = "用户表接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/user")
public class ShUserController {
    @Autowired
    private IShUserService iShUserService;
    @Autowired
    private ShUserMapper shUserMapper;

    @GetMapping("/list")
    @ApiOperation("查询全部")
    public List<ShUser> getAll(){
        return shUserMapper.selectList(null);
    }

    @PutMapping()
    @ApiOperation("修改User")
    public boolean updateUser(@RequestBody ShUser shUser){
        iShUserService.updateById(shUser);
        return true;
    }
    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public Boolean deleteUser(@PathVariable String id){
        return iShUserService.removeById(id);
    }

    @PostMapping("")
    @ApiOperation("添加用户")
    public String addHotel(@RequestBody ShUser shUser){
        shUser.setId(UUIDUtils.getCharAndNumr());
        shUserMapper.insert(shUser);
        return "添加成功";
    }

}
