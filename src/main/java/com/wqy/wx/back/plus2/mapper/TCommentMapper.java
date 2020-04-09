package com.wqy.wx.back.plus2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqy.wx.back.plus2.entity.TComment;
import org.apache.ibatis.annotations.Delete;

/**
 * <p>
 * 评论投诉表i Mapper 接口
 * </p>
 *
 * @author licm
 * @since 2020-04-03
 */
public interface TCommentMapper extends BaseMapper<TComment> {

    @Delete("delete from t_comment where product_id = #{id}")
    void deleteProduct(String id);
}
