package com.wqy.modules.shopping.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wqy.modules.shopping.entity.ShCart;
import com.wqy.modules.shopping.mapper.ShCartMapper;
import com.wqy.modules.shopping.service.IShCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ShCartServiceImpl extends ServiceImpl<ShCartMapper, ShCart> implements IShCartService {
    @Autowired
    private ShCartMapper shCartMapper;
    @Override
    @Transactional
    public void addCart(ShCart shCart) {
        //生成UUID唯一标识符作为Id
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        //截取
        String substring = s.substring(0, 16);
        shCart.setId(substring);
        shCartMapper.addCart(shCart);
    }

    @Override
    @Transactional
    public void updateCart(ShCart shCart) {
        shCartMapper.updateCart(shCart);
    }

    @Override
    public List<ShCart> selectAll(String userId) {
        return shCartMapper.selectAll(userId);
    }

    @Override
    @Transactional
    public void deleteCart(String goodsId, String userId) {
        shCartMapper.deleteCart(goodsId, userId);
    }
}
