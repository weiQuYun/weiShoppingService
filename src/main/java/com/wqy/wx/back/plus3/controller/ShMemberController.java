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
@RequestMapping(Constant.MAPPING + "/member")
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

    @PostMapping("")
    public Boolean addMember(@RequestBody ShMember shMember) {
        return iShMemberService.addMember(shMember);
    }

    /**
     * 修改
     **/
    @PutMapping(value = "")
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
