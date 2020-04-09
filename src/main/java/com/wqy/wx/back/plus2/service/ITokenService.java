package com.wqy.wx.back.plus2.service;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: ITokenService
 * @Description: TODO
 * @Author licm
 * @Date 2020/4/6 11:19
 * @Version V1.0
 */
public interface ITokenService {

    /**
     * 获取认证
     *
     * @param user
     * @return
     */
    String getToken(String user);

    /**
     * 获取认证
     *
     * @param userName
     * @param password
     * @return
     */
    String getToken(String userName, String password);
}
