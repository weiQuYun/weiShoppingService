package com.wqy.wx.back.configer.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: BizException
 * @Description: TODO
 * @Author licm
 * @Date 2020/1/3 18:42
 * @Version V1.0
 * @Explain :
 **/
@Data
@Component
@Slf4j
public class BizException extends RuntimeException implements Serializable {

    @Autowired
    private static ErrorBean errorBean;

    public BizException() {
        super();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    protected BizException(String message, Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BizException(String errorCode, String... errorMsg) {
        super(errorBean.getFront(errorCode, errorMsg));
        log.error(errorBean.getBack(errorCode, errorMsg));
    }
}
