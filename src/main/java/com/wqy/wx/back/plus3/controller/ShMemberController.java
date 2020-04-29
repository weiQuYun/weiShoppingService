package com.wqy.wx.back.plus3.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.dto.AddressDto;
import com.wqy.wx.back.dto.WxPatDto;
import com.wqy.wx.back.plus3.entity.ShMember;
import com.wqy.wx.back.plus3.entity.ShOrder;
import com.wqy.wx.back.plus3.mapper.ShOrderMapper;
import com.wqy.wx.back.plus3.service.IShMemberService;
import com.wqy.wx.back.plus3.service.IShOrderService;
import com.wqy.wx.back.plus3.service.impl.ShareIDChange;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author licm
 * @since 2020-04-09
 */
@Api(tags = "会员表接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/member")
public class     ShMemberController {
    @Autowired
    private IShMemberService iShMemberService;
    @Autowired
    private ShOrderMapper shOrderMapper;
    @Autowired
    private ShareIDChange shareIDChange;
    //偷下懒 LCX
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
    @PostMapping("/one")
    @ApiOperation("查询单个用户")
    public ShMember selectById(@RequestBody String id){
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

    @PostMapping("/s")
    @ApiOperation("新增用户")
    public Boolean addMembers(String code,String parentId){
        System.out.println(code);
        System.out.println(parentId);
        //String parentLong = shareIDChange.getParentLong(parentId);
        return iShMemberService.addMembers(code,parentId);
    }
    //向下查三层 权老板 你倒是写了啊。。。。。。。。。。。。。。。。。4点还要加接口
    @GetMapping("/downThree/{id}")
    @ApiOperation("向下查三层")
    public List<ShMember> getDownThree(@PathVariable("id") String id){
        return iShMemberService.selectDownThree(id);
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
    @PostMapping("/address")
    @ApiOperation("开完会员调用这个给我一个地址")
    @Transactional
    public Boolean setAddress(@RequestBody AddressDto addressDto){
        ShOrder shOrder = new ShOrder();
        shOrder.setId(0);
        shOrder.setOrderId(UUIDUtils.getCharAndNumr());
        shOrder.setReceiver(addressDto.getName());
        shOrder.setAddress(addressDto.getAddress());
        shOrder.setTel(addressDto.getTell());
        shOrder.setZcode("000000");
        shOrder.setTotalPrice(new BigDecimal(0));
        shOrder.setMemberId(addressDto.getMemberID());
        shOrder.setPayStatus(1);
        shOrder.setSendStatus(0);
        shOrder.setAliOrderId("已付款");
        shOrder.setCompany("快递公司");
        shOrder.setNumber("快递编号");
        shOrder.setCreateTime(new Date());
        shOrder.setUpdateTime(new Date());
        shOrderMapper.insert(shOrder);
        return true;
    }

    @PostMapping("/vipOrder")
    @ApiOperation("开会员先调用这个接口生成订单,传用户id和要开的vip等级给我")
    public WxPatDto vipOrder(@RequestBody ShMember shMember){
        WxPatDto wxPatDto = iShMemberService.vipOrder(shMember);
        return wxPatDto;

    }
    @GetMapping("/parentId/{id}")
    @ApiOperation("获取自己的推荐码")
    public String getShareId(@PathVariable("id") String id){
        return shareIDChange.getParentsort(id);
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
