package com.wqy.wx.back.configer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: GlobalExceptionHandler
 * @Description: TODO
 * @Author licm
 * @Date 2020/1/3 18:49
 * @Version V1.0
 * @Explain :
 **/
@Slf4j
@Component
@RestControllerAdvice(basePackages = {"com.wqy.wx.nack"})
public class GlobalExceptionHandler {
    @Autowired
    private ErrorBean errorBean;

    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public ResultBody bizExceptionHandler(HttpServletRequest req, BizException e) {
        log.error("自定义的业务异常！原因是:", e);
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR, e);
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ResultBody exceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("发生空指针异常！原因是:", e);
        return ResultBody.error(CommonEnum.BODY_NOT_MATCH, e);
    }

    /**
     * 处理sql异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BadSqlGrammarException.class)
    public ResultBody exceptionHandler(HttpServletRequest req, BadSqlGrammarException e) {
        log.error("sql异常！原因是:", e);
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR, e);
    }

    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常！原因是:", e);
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR, e);
    }
}
