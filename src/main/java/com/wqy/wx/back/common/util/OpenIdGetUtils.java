package com.wqy.wx.back.common.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OpenIdGetUtils {
    private static StringBuilder stringBuilder;

    public static Map<String, String> getOpenId(String code) throws IOException {
        stringBuilder = new StringBuilder();
        Map<String,String> map = new HashMap<String, String>();
        stringBuilder.append("https://api.weixin.qq.com/sns/jscode2session?appid=");
        stringBuilder.append("wx957c09a910f03c61");         //这里是微信小程序的appid
        stringBuilder.append("&secret=");
        stringBuilder.append("55412b3e0bab089a0bc80e52e543843b");       //这是appsecret
        stringBuilder.append("&js_code=");
        stringBuilder.append(code);
        stringBuilder.append("&grant_type=authorization_code");
        Document document = Jsoup.connect(stringBuilder.toString()).get();
        System.out.println(document.text());
        String text = document.text();
        String[] split = text.split("\"");
        System.out.println(split.length);
        String session = split[1];
        String sessionKey = split[3];
        String openId = split[5];
        String openIdKey = split[7];
        map.put(session,sessionKey);
        map.put(openId,openIdKey);
        return map;
    }

}
