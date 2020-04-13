package com.wqy.wx.back.configer.exception;

import com.wqy.wx.back.common.util.StringUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "owner-exception")
public class ErrorBean {
    private Map<String, String> front;
    private Map<String, String> back;

    public String getFront(String code, String... msg) {

        return StringUtil.format(this.front.get(code), msg);
    }

    public String getBack(String code, String... msg) {
        return StringUtil.format(this.back.get(code), msg);
    }
}
