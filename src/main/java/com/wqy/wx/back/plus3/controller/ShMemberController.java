package com.wqy.wx.back.plus3.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus3.entity.ShMember;
import com.wqy.wx.back.plus3.service.IShMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author licm
 * @since 2020-04-09
 */
@Api(tags = "会员表接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/member")
public class ShMemberController {
    @Autowired
    private IShMemberService iShMemberService;
    /*
     *  查询全部
     * */
    @PostMapping("findAll")
    @ApiOperation("查询全部用户")
    public Page<ShMember> findAll(PageDTO pageDTO, ShMember shMember){
        return iShMemberService.selectAll(pageDTO,shMember);
    }
    /**
     * 根据id查询单个用户
     */
    @GetMapping("/{id}")
    @ApiOperation("查询单个用户")
    public ShMember selectById(@PathVariable String id){
        return iShMemberService.selectById(id);
    }
    /**
     * 新增用户
     */
    @PostMapping("")
    @ApiOperation("新增用户 推人")
    public Boolean addMember(@RequestBody ShMember shMember) {
        return iShMemberService.addMember(shMember);
    }

    /**
     *
     * @param id
     * @param lvVip
     * @return
     */
    @PutMapping(value = "")
    @ApiOperation("开会员")
    public Boolean updateVip(@RequestParam String id, @RequestParam Integer lvVip) {
        System.out.println(id);
        iShMemberService.selectById(id);
        iShMemberService.addVipMember(lvVip, id);
        return true;

    }
    /**
     * 删除
     * **/
   /* @DeleteMapping(value = "/delete/{id}")
    public Result deleteShUser(@PathVariable String id){
        shTypeService.deleteType(id);
        return new Result(true,StatusCode.OK,"成功");
    }*/
}
