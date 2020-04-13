package com.wqy.wx.back.plus3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.plus3.entity.ShExchangeReq;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 积分兑换申请表 服务类
 * </p>
 *
 * @author licm
 * @since 2020-04-11
 */
public interface IShExchangeReqService extends IService<ShExchangeReq> {

    /**
     * 添加提现请求
     * @param shExchangeReq
     * @return
     */
     Boolean request(ShExchangeReq shExchangeReq);

    /**
     * 条件查询提现请求
     * @param shExchangeReq
     * @return
     */
     List<ShExchangeReq> getList(ShExchangeReq shExchangeReq);

    /**
     * 下载文件
     * @param date
     * @param response
     * @return
     */
    void downloadFile(String date,HttpServletResponse response);
}
