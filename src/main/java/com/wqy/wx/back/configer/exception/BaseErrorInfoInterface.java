package com.wqy.wx.back.configer.exception;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: BaseErrorInfoInterface
 * @Description: TODO
 * @Author licm
 * @Date 2020/1/3 18:40
 * @Version V1.0
 * @Explain :
 **/
public interface BaseErrorInfoInterface {
    /**
     * 错误码
     */
    String getResultCode();

    /**
     * 错误描述
     */
    String getResultMsg();
}
