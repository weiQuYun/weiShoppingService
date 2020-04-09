package com.wqy.modules.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wqy.modules.common.pojo.Page;
import com.wqy.modules.shopping.entity.ShGoods;
import com.wqy.modules.shopping.entity.ShGoodsAttr;
import com.wqy.modules.shopping.entity.ShType;
import com.wqy.modules.shopping.mapper.ShGoodsAttrMapper;
import com.wqy.modules.shopping.mapper.ShGoodsMapper;
import com.wqy.modules.shopping.service.IShGoodsService;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.session.RowBounds;
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
public class ShGoodsServiceImpl implements IShGoodsService {
    @Autowired
    private ShGoodsMapper shGoodsMapper;
    @Autowired
    private ShGoodsAttrMapper shGoodsAttrMapper;

    /**
     * 查询全部+分页+高级查询（商品名字）
     * @param page
     * @param shGoods
     * @return
     */
    @Override
    public PageInfo<ShGoods> selectAll(Page page, ShGoods shGoods) {
        int pageNumber=(int)page.getCurrentpage();
        int pageSize=(int)page.getSize();
        PageHelper.startPage(pageNumber, pageSize,true,true,true);
        List<ShGoods> shGoods1 = shGoodsMapper.selectAll(shGoods);
        PageInfo pageInfo = new PageInfo(shGoods1);
        System.out.println(pageInfo.toString());
        return pageInfo;
    }

    /**
     * 添加
     * @param shGoods
     */
    @Transactional
    @Override
    public void addGoods(ShGoods shGoods) {
        //生成UUID唯一标识符作为Id
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        //截取
        String substring = s.substring(0, 16);
        shGoods.setId(substring);
        //生成UUID唯一标识符作为系统自动生成的唯一货号
        UUID uuid1 = UUID.randomUUID();
        String s2=uuid1.toString();
        //截取
        String substring1=s2.substring(0,16);
        shGoods.setGoodsSn(substring1);
        //获取当前时间
        Date date = new Date();
        shGoods.setCreateTime(date);
        shGoods.setUpdateTime(date);
        shGoodsMapper.addGoods(shGoods);
    }

    /**
     * 修改
     * @param shGoods
     */
    @Override
    @Transactional
    public void updateGoods(ShGoods shGoods) {
        shGoodsMapper.updateGoods(shGoods);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional
    public void deleteGoods(String id) {
        //删除商品前要先删除关联到该商品主键的
        shGoodsAttrMapper.deleteByGoods(id);
        shGoodsMapper.deleteGoods(id);
    }

    /**
     * 上架
     * @param id
     */
    @Override
    public void putawayGoods(String id) {
        shGoodsMapper.putawayGoods(id);
    }

    /**
     * 下架
     * @param id
     */
    @Override
    public void soldOutGoods(String id) {
        shGoodsMapper.soldOutGoods(id);
    }

    @Override
    public ShGoods selectById(String id) {
        return shGoodsMapper.selectById(id);
    }

    /**
     * 通过id查询
     */
    public ShGoods selectByid(String id){
        return shGoodsMapper.selectById(id);
    }
}
