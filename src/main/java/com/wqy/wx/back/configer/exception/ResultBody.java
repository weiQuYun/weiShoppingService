package com.wqy.wx.back.configer.exception;

import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: ResultBody
 * @Description: TODO
 * @Author licm
 * @Date 2020/1/3 18:43
 * @Version V1.0
 * @Explain :
 **/
public class ResultBody {
    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object result;

    public ResultBody() {
    }

    public ResultBody(BaseErrorInfoInterface errorInfo) {
        this.code = errorInfo.getResultCode();
        this.message = errorInfo.getResultMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * 成功
     *
     * @return
     */
    public static ResultBody success() {
        return success(null);
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static ResultBody success(Object data) {
        ResultBody rb = new ResultBody();
        rb.setCode(CommonEnum.SUCCESS.getResultCode());
        rb.setMessage(CommonEnum.SUCCESS.getResultMsg());
        rb.setResult(data);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error(BaseErrorInfoInterface errorInfo, Exception e) {
        ResultBody rb = new ResultBody();
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMsg());
        rb.setResult(getExceptionInformation(e));
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error(String code, String message, Exception e) {
        ResultBody rb = new ResultBody();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setResult(e.getStackTrace());
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error(String message) {
        ResultBody rb = new ResultBody();
        rb.setCode("-1");
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    /**
     * 异常解析
     */
    public static String getExceptionInformation(Exception exception) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        exception.printStackTrace(printStream);

        String exceptionInformation = new String(outputStream.toByteArray());
        printStream.close();
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exceptionInformation;
    }
}
