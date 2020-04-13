package com.wqy.wx.back.configer.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * JSON信息交互对象模板
 *
 * @Author licm
 * @Date 2019/4/8 23:46
 * @Description
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Result {

    /**
     * 响应码
     */
    private String code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;
    /**
     * 请求地址
     */
    private String url;

    public Result() {
        this.data = data;
    }

    public Result(Object data) {
        this.data = data;
    }

}