package com.wqy.wx.back.common.util;

import com.wqy.wx.back.configer.exception.BizException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OpenIdGetUtils {
    private static StringBuilder stringBuilder;

    public static Map<String, String> getOpenId(String code) {
        stringBuilder = new StringBuilder();
        System.out.println("==============");
        System.out.println(code);
        Map<String,String> map = new HashMap<String, String>();
        try{
            stringBuilder.append("https://api.weixin.qq.com/sns/jscode2session?appid=");
            stringBuilder.append("wxb9ce31e98edf6711");         //这里是微信小程序的appid wx62b66d70c930c6ae
           // stringBuilder.append("wx62b66d70c930c6ae");         //这里是微信小程序的appid wx62b66d70c930c6ae
            stringBuilder.append("&secret=");
            stringBuilder.append("96e7721a4f4614469b96c631f00c315c");       //这是appsecret
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
            for (String s : split) {
                System.out.println(s);
            }
            map.put(session,sessionKey);
            map.put(openId,openIdKey);

        }catch (IOException e){
            throw  new BizException("OpenId解析失败");
        }catch (ArrayIndexOutOfBoundsException a){
            throw new BizException("你传递的code有问题");
        }
        return map;
    }

}
