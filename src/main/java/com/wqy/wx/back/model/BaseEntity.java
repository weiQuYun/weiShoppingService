package com.wqy.wx.back.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.wqy.wx.back.common.util.UUIDUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: BaseEntity
 * @Description: TODO
 * @Author licm
 * @Date 2020/3/7 23:29
 * @Version V1.0
 * @Explain :
 **/
@Slf4j
@Data
public abstract class BaseEntity<T extends Model<?>> extends Model<T> implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.INPUT)
    private String id = UUIDUtils.getCharAndNumr();
    /**
     * 创建日期 - 现在时表示主动创建
     */
    @ApiModelProperty(value = "创建日期", example = "1900/01/01 00:00:00")
    private Date createTime = new Date();

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    /**
     * 重写添加方法
     *
     * @return
     */
    @Transactional
    @Override
    public boolean insert() {
        SqlSession sqlSession = this.sqlSession();
        boolean var3;
        try {
            var3 = SqlHelper.retBool(this.sqlSession().insert(this.sqlStatement(SqlMethod.INSERT_ONE), this));
        } finally {
            this.closeSqlSession(sqlSession);
        }
        return var3;
    }

    /**
     * 重写添加或者修改方法
     *
     * @return
     */
    @Transactional
    @Override
    public boolean insertOrUpdate() {
        if (!StringUtils.checkValNull(this.pkVal()) && !Objects.isNull(this.selectById(this.pkVal()))) {
            return this.updateById();
        } else {
            return this.insert();
        }
    }

    @Transactional
    @Override
    public boolean updateById() {
        Assert.isFalse(StringUtils.checkValNull(this.pkVal()), "修改时主键为空", new Object[0]);
        Map<String, Object> map = new HashMap(1);
        map.put("et", this);
        SqlSession sqlSession = this.sqlSession();
        boolean var3;
        try {
            var3 = SqlHelper.retBool(sqlSession.update(this.sqlStatement(SqlMethod.UPDATE_BY_ID), map));
        } finally {
            this.closeSqlSession(sqlSession);
        }
        return var3;
    }

    @Override
    public boolean deleteById() {
        Assert.isFalse(StringUtils.checkValNull(this.pkVal()), "deleteById primaryKey is null.", new Object[0]);
        SqlSession sqlSession = this.sqlSession();
        boolean var3;
        try {
            var3 = SqlHelper.retBool(sqlSession.delete(this.sqlStatement(SqlMethod.DELETE_BY_ID), this.pkVal()));
        } finally {
            this.closeSqlSession(sqlSession);
        }
        return var3;
    }

    @Override
    public boolean delete(Wrapper<T> queryWrapper) {
        Map<String, Object> map = new HashMap(1);
        map.put("ew", queryWrapper);
        SqlSession sqlSession = this.sqlSession();
        boolean var4;
        try {
            var4 = SqlHelper.retBool(sqlSession.delete(this.sqlStatement(SqlMethod.DELETE), map));
        } finally {
            this.closeSqlSession(sqlSession);
        }
        return var4;
    }

    /**
     * 禁用
     *
     * @return
     */
    public boolean prohibitById() {
        Assert.isFalse(null == this.id, "删除时主键为空", new Object[0]);
        SqlSession sqlSession = this.sqlSession();
        boolean var3;
        Map<String, Object> map = new HashMap(1);
        map.put("et", this);
        try {
            var3 = SqlHelper.retBool(sqlSession.update(this.sqlStatement(SqlMethod.UPDATE_BY_ID), map));
        } finally {
            this.closeSqlSession(sqlSession);
        }
        return var3;
    }

    /**
     * 批量禁用
     *
     * @param updateWrapper
     * @return
     */
    public boolean prohibit(Wrapper<T> updateWrapper) {
        Map<String, Object> map = new HashMap(2);
        map.put("et", this);
        map.put("ew", updateWrapper);
        SqlSession sqlSession = this.sqlSession();

        boolean var4;
        try {
            var4 = SqlHelper.retBool(sqlSession.update(this.sqlStatement(SqlMethod.UPDATE), map));
        } finally {
            this.closeSqlSession(sqlSession);
        }

        return var4;
    }

    @Override
    public List<T> selectAll() {
        Map<String, Object> map = new HashMap(1);
        Wrapper<T> queryWrapper = new QueryWrapper<>();
        map.put("ew", queryWrapper);
        SqlSession sqlSession = this.sqlSession();
        List var2;
        try {
            var2 = sqlSession.selectList(this.sqlStatement(SqlMethod.SELECT_LIST));
        } finally {
            this.closeSqlSession(sqlSession);
        }
        return var2;
    }

    @Override
    public T selectById() {
        Assert.isFalse(StringUtils.checkValNull(this.pkVal()), "selectById primaryKey is null.", new Object[0]);
        T t = this.selectById(this.pkVal());
        return t;
    }

    @Override
    public List<T> selectList(Wrapper<T> queryWrapper) {
        Map<String, Object> map = new HashMap(1);
        QueryWrapper<T> tQueryWrapper = (QueryWrapper<T>) queryWrapper;
        map.put("ew", tQueryWrapper);
        SqlSession sqlSession = this.sqlSession();
        List var4;
        try {
            var4 = sqlSession.selectList(this.sqlStatement(SqlMethod.SELECT_LIST), map);
        } finally {
            this.closeSqlSession(sqlSession);
        }

        return var4;
    }
}
