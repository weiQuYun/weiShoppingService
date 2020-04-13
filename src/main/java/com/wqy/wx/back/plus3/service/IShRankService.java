package com.wqy.wx.back.plus3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.plus3.entity.ShRank;

import java.util.List;

/**
 * <p>
 * 团队分值排行榜 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-10
 */
public interface IShRankService extends IService<ShRank> {

    /**
     * 生成排行榜
     */
    void generateRank();

    /**
     * 条件查询
     *
     * @param shRank
     * @return
     */
    List<ShRank> getList(ShRank shRank);
}
