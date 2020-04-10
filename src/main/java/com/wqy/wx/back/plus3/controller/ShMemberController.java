package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus3.entity.ShMember;
import com.wqy.wx.back.plus3.service.IShMemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author licm
 * @since 2020-04-09
 */
@Api(tags = "会员表接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/shMember")
public class ShMemberController {
    @Autowired
    private IShMemberService iShMemberService;
    /*
     *  查询全部
     * */
   /* @PostMapping("findAll")
    public PageInfo<ShType> findAll(Page page,@RequestBody ShType shType){
        return shTypeService.selectAll(page,shType);
    }*/
    /**
     * 新增用户
     */
    @Autowired
    private IShMemberService iShMemberService;
    @PostMapping("/batch")
    public Boolean addMember(@RequestBody ShMember shMember){
        iShMemberService.addMember(shMember);
        return true;
    }
    /**
     * 修改
     * **/
    @PutMapping(value = "/update/{id}")
    public Boolean updateShUser(@PathVariable Integer lvVip, @PathVariable String id){
        iShMemberService.selectById(id);
        iShMemberService.addVipMember(lvVip,id);
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
