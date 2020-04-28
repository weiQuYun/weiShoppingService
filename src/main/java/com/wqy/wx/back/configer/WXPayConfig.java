package com.wqy.wx.back.configer;

public class WXPayConfig {
    //小程序appid  开发者自己拥有的
    public static final String appid = "wxb9ce31e98edf6711";                              //微信分配的小程序ID 暂时用的我的必须改成客户的
    //微信支付的商户id   开发者去问自己id的前端同事或者领导。
    public static final String mch_id = "1561443391";                             //商户ID 微信支付分配的商户号
    //微信支付的商户密钥  开发者问领导，去微信商户平台去申请（要下插件等等）
    public static final String key = "947821Cp8j07F03P834983T22Ena4734";                                //这个KEy是什么不是很确定 上小程序网上看看 大致是在 key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
    //支付成功后的服务器回调url，这里填PayController里的回调函数地址
    public static final String notify_url = "https://localhost:60010/pay/wxNotify";                         //这里的地址是https://www.baidu.com/wxNotify 即pageDemo 67行
    //签名方式，固定值
    public static final String SIGNTYPE = "MD5";
    //交易类型，小程序支付的固定值为JSAPI
    public static final String trade_type = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
