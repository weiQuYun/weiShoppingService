package com.wqy.wx.back.plus3.service.impl;

import com.wqy.wx.back.common.util.ShGoodsPricePlus;
import com.wqy.wx.back.dto.TemporaryMember;
import com.wqy.wx.back.plus3.entity.ShGoods;
import com.wqy.wx.back.plus3.entity.ShIndex;
import com.wqy.wx.back.plus3.entity.ShMember;
import com.wqy.wx.back.plus3.mapper.ShGoodsMapper;
import com.wqy.wx.back.plus3.mapper.ShIndexMapper;
import com.wqy.wx.back.plus3.mapper.ShMemberMapper;
import com.wqy.wx.back.plus3.service.IShIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Primary

public class ShIndexServiceImpl implements IShIndexService {
    @Autowired
    private ShIndexMapper shIndexMapper;
    @Autowired
    private ShMemberMapper shMemberMapper;
    @Autowired
    private ShGoodsMapper shGoodsMapper;

    @Override
    public List<ShIndex> getNotice() {
        //直接获取所有首页表 拿去最后一个 因为首页公告表为Id自增表 直接获取所有公告之后拿去最后一个元素即可
        List<ShIndex> shIndex = shIndexMapper.selectList(null);
        //获取最后一个元素直接返回
        return shIndex;
    }

    /**
     * 修改新增首页公告表
     **/
    @Override
    @Transactional
    public boolean save(ShIndex shIndex) {
        shIndexMapper.insert(shIndex);
        return true;
    }

    /**
     * 返回首页滚动条数据 默认为最新的几条数据
     **/
    @Override
    public List<TemporaryMember> getTemporaryMember() {
        //用于主页滚动条
        List<TemporaryMember> list = new ArrayList<>();
        Date date = new Date(new Date().getTime() -1000000);
        //获取最新的更新用户表
        List<ShMember> memberList = shMemberMapper.selectByTime(date);
        if (memberList.size() > 0) {
            for (int i = 0; i < memberList.size(); i++) {
                ShMember shMember = memberList.get(i);
                String usernameSon = shMember.getUsername();//用户名
                if (!shMember.getParentId().equalsIgnoreCase("")) {
                    //如果父id不是空则这个用户是推荐过来的
                    //获取父会员
                    ShMember shMemberParent = shMemberMapper.selectByParentId(shMember.getParentId());
                    if (shMemberParent == null) {
                        continue;
                    }
                    String usernameParent = shMemberParent.getUsername();
                    //新建返回值对象
                    TemporaryMember<List<ShMember>> shMemberTemporaryMember = new TemporaryMember<>();
                    shMemberTemporaryMember.setMemberSon(usernameSon);
                    shMemberTemporaryMember.setMemberParent(usernameParent);
                    List<ShMember> memberList1 = new ArrayList<>();
                    //简单的遮一下
                    shMember.setPassword("");
                    shMemberParent.setPassword("");
//                    memberList1.add(shMember);
//                    memberList1.add(shMemberParent);
//                    shMemberTemporaryMember.setData(memberList1);
                    list.add(shMemberTemporaryMember);
                }
            }
        } else {
            return null;
        }
        return list;
    }

    @Override
    public List<ShGoods> getIndexShGoods() {
        List<ShGoods> list = shGoodsMapper.selectByIndex("indexshgoods");
        return ShGoodsPricePlus.add(list);
      //  return list;
    }

    @Override
    public List<ShGoods> getIndexHotShGoods() {
        List<ShGoods> list = shGoodsMapper.selectByIsHot();
        return ShGoodsPricePlus.add(list);
       // return list;
    }
}
