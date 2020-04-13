package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.configer.exception.BizException;
import com.wqy.wx.back.plus3.entity.ShMember;
import com.wqy.wx.back.plus3.entity.ShRank;
import com.wqy.wx.back.plus3.mapper.ShRankMapper;
import com.wqy.wx.back.plus3.service.IShRankService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 团队分值排行榜 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-10
 */
@Primary
@Service
public class ShRankServiceImpl extends ServiceImpl<ShRankMapper, ShRank> implements IShRankService {

    @Autowired
    private ShRankMapper shRankMapper;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = BizException.class)
    @Override
    public void generateRank() {
        //查询所有团长
        QueryWrapper<ShRank> shRankQueryWrapper = new QueryWrapper<>();
        shRankQueryWrapper.isNotNull("id");
        //删除所有
        new ShRank().delete(shRankQueryWrapper);
        QueryWrapper<ShMember> shMemberQueryWrapper = new QueryWrapper<>();
        shMemberQueryWrapper.eq("ifs_captain", "1");
        List<ShMember> shMembers = new ShMember().selectList(shMemberQueryWrapper);
        //递归查询所有相关人员的积分
        if (CollectionUtils.isNotEmpty(shMembers)) {
            if (CollectionUtils.isNotEmpty(shMembers)) {
                List<ShRank> list = new ArrayList<>();
                for (ShMember shMember : shMembers) {
                    long grade = round(shMember.getParentId());
                    ShRank rank = new ShRank();
                    rank.setCaptainName(shMember.getCaptainName());
                    rank.setMemberId(shMember.getId());
                    rank.setGrade(grade);
                    list.add(rank);
                }
                //排序 存放
                list = list.parallelStream().sorted(Comparator.comparing(ShRank::getGrade)).collect(Collectors.toList());
                int i = list.size();
                for (ShRank rank : list) {
                    rank.setId(i);
                    rank.insert();
                    i--;
                }
            }
        }
    }

    @Override
    public List<ShRank> getList(ShRank shRank) {
        QueryWrapper<ShRank> shRankQueryWrapper = new QueryWrapper<>();
        shRankQueryWrapper = ParamUtils.reflect(shRank, shRankQueryWrapper);
        return shRankMapper.selectList(shRankQueryWrapper);
    }

    /**
     * 递归计算
     *
     * @param id
     * @return
     */
    private long round(String id) {
        QueryWrapper<ShMember> shMemberQueryWrapper = new QueryWrapper<>();
        shMemberQueryWrapper.eq("parent_id", id);
        List<ShMember> shMembers = new ShMember().selectList(shMemberQueryWrapper);
        long grade = 0L;
        if (CollectionUtils.isNotEmpty(shMembers)) {
            for (ShMember shMember : shMembers) {
                grade = grade + round(shMember.getParentId());
            }
        }
        return grade;
    }
}
