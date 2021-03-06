package com.wqy.wx.back.plus3.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.dto.WxPatDto;
import com.wqy.wx.back.plus3.entity.ShMember;
import com.wqy.wx.back.plus3.entity.ShOrder;

import java.util.List;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
public interface IShMemberService extends IService<ShMember> {
    /**
     * 新增会员
     *
     * @param shMember
     */
    Boolean addMember(ShMember shMember);

    /**
     * 开会员
     */
    void addVipMember(Integer lvVip, String id);

    /**
     * 根据id查询
     */
    ShMember selectById(String id);

    /**
     * 开通会员后返点
     */
    void rebatesIntegral(Long integral, String id);

    /**
     * 查询全部用户
     */
    Page<ShMember> selectAll(PageDTO pageDTO, ShMember shMember);

    /**
     * 新增会员
     * @param code
     * @param parentId
     * @return
     */
    Boolean addMembers(String code, String parentId);


    List<ShMember> selectDownThree(String id);

    /**
     * 执行vip接口前先生成vip的订单
     * @param shMember
     * @return
     */
    WxPatDto vipOrder(ShMember shMember);
}
