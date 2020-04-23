package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.plus3.entity.ShType;
import com.wqy.wx.back.plus3.mapper.ShTypeMapper;
import com.wqy.wx.back.plus3.service.IShTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-09
 */
@Slf4j
@Primary
@Service
public class ShTypeServiceImpl extends ServiceImpl<ShTypeMapper, ShType> implements IShTypeService {

    @Autowired
    private ShTypeMapper shTypeMapper;

    @Override
    public List<ShType> selectAll() {
        List<ShType> shTypes = shTypeMapper.selectList(null);
        ShType indexshgoods = shTypeMapper.selectById("indexshgoods");
        shTypes.remove(indexshgoods);
        return shTypes;
    }

    @Override
    public ShType selectIndex() {
        return shTypeMapper.selectById("indexshgoods");
    }

    @Override
    public Boolean updateShType(ShType shType) {
        shTypeMapper.updateById(shType);
        return true;
    }

    @Override
    public Boolean deleteById(String id) {
        shTypeMapper.deleteById(id);
        return true;
    }

    @Override
    public Boolean insertShType(ShType shType) {
        shType.setId(UUIDUtils.getCharAndNumr());
        shTypeMapper.insert(shType);
        return true;
    }

    @Override
    public boolean updateShType(List<ShType> list) {
        if (list.size() > 0) {
            for (ShType shType : list) {
                updateShType(shType);
            }
        }
        return true;
    }

    @Override
    public boolean insertShType(List<ShType> list) {
        if (list.size() > 0) {
            for (ShType shType : list) {
                insertShType(shType);
            }
        }
        return true;
    }

    @Override
    public boolean deleteShType(List<String> list) {
        if (list.size() > 0) {
            for (String s : list) {
                deleteById(s);
            }
        }
        return true;
    }

    @Override
    public List<ShType> selectAll(Integer id) {
        if (id==0){
            return shTypeMapper.selectList(null);
        }else {
            List<ShType> shTypes = shTypeMapper.selectList(null);
            ShType indexshgoods = shTypeMapper.selectById("indexshgoods");
            shTypes.remove(indexshgoods);
            return shTypes;
        }
    }
}
