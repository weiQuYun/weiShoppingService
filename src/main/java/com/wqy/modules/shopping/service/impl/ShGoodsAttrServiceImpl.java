package com.wqy.modules.shopping.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wqy.modules.common.pojo.Page;
import com.wqy.modules.shopping.entity.ShGoods;
import com.wqy.modules.shopping.entity.ShGoodsAttr;
import com.wqy.modules.shopping.mapper.ShGoodsAttrMapper;
import com.wqy.modules.shopping.mapper.ShGoodsMapper;
import com.wqy.modules.shopping.service.IShGoodsAttrService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-01
 */
@Service
public class ShGoodsAttrServiceImpl extends ServiceImpl<ShGoodsAttrMapper, ShGoodsAttr> implements IShGoodsAttrService {
    @Autowired
    private ShGoodsAttrMapper shGoodsAttrMapper;

    public void addGoodsAttr(ShGoodsAttr shGoodsAttr) {
        //生成UUID唯一标识符作为Id
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        //截取
        String substring = s.substring(0, 16);
        shGoodsAttr.setId(substring);
        //获取当前时间
        Date date = new Date();
        shGoodsAttr.setCreateTime(date);
        shGoodsAttr.setUpdateTime(date);
        shGoodsAttrMapper.addGoodsAttr(shGoodsAttr);
    }

    @Override
    public void updateGoodsAttr(ShGoodsAttr shGoodsAttr) {
        Date date = new Date();
        shGoodsAttr.setUpdateTime(date);
        shGoodsAttrMapper.updateGoodsAttr(shGoodsAttr);
    }
    @Transactional
    @Override
    public void deleteGoodsAttr(String id) {

        shGoodsAttrMapper.deleteGoodsAttr(id);
    }

    @Override
    public PageInfo<ShGoods> selectAll(Page page) {
        int pageNumber=(int)page.getCurrentpage();
        int pageSize=(int)page.getSize();
        PageHelper.startPage(pageNumber, pageSize,true,true,true);
        List<ShGoodsAttr> shGoodsAttrs = shGoodsAttrMapper.selectAll();
        PageInfo pageInfo = new PageInfo(shGoodsAttrs);
        return pageInfo;
    }

    @Transactional
    @Override
    public void deleteByGoods(String goodsId) {
        shGoodsAttrMapper.deleteByGoods(goodsId);
    }
    @Transactional
    @Override
    public void deleteByAttr(String attrId) {
        shGoodsAttrMapper.deleteByAttr(attrId);
    }
}
