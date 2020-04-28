package com.wqy.wx.back.plus3.service.impl;


import com.wqy.wx.back.common.util.PayUtils;
import com.wqy.wx.back.common.util.UUIDUtils;
import com.wqy.wx.back.configer.WXPayConfig;
import com.wqy.wx.back.plus3.entity.ShGoods;
import com.wqy.wx.back.plus3.entity.ShOrder;
import com.wqy.wx.back.plus3.entity.ShOrderGoods;
import com.wqy.wx.back.plus3.mapper.ShGoodsMapper;
import com.wqy.wx.back.plus3.mapper.ShOrderGoodsMapper;
import com.wqy.wx.back.plus3.mapper.ShOrderMapper;
import com.wqy.wx.back.plus3.service.WXPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 必要字段
 * appid:微信小程序ID           暂无
 * mch_id:微信商户号ID           暂无
 * nonce_str:随机字符串         √
 *sign:签名:需要MD5加密          √
 * body:商品描述                √
 * out_trade_no:商户订单号即我们自己服务器的订单号   √
 * total_fee:订单金额 需要int   √
 * spbill_create_ip:终端IP     √
 * notify_url:回调函数位置 后端接口       √
 * trade_type:JSAPI  默认值    √
 * **/
@Service
public class WXPayServiceImpl implements WXPayService {
    @Autowired
    private ShGoodsMapper shGoodsMapper;
    @Autowired
    private ShOrderMapper shOrderMapper;
    @Autowired
    private ShOrderGoodsMapper shOrderGoodsMapper;
    @Override
    public Object wxPay(String spbill_create_ip, String out_trade_no, String openId) {
        ShOrder shOrder = shOrderMapper.selectByOrderId(out_trade_no); //根据订单号查询订单
        String orderId = shOrder.getOrderId(); //查询订单的id
        BigDecimal totalPrice = shOrder.getTotalPrice(); //查询订单的总价格  商品价格
        ShOrderGoods shOrderGoods = shOrderGoodsMapper.selectByOrderId(orderId); //根据订单id查询订单详情
        String goodsId = shOrderGoods.getGoodsId(); //获取商品id
        ShGoods goods = shGoodsMapper.selectById(goodsId); //获取到指定的商品
        String goodsName = goods.getGoodsName(); //获取到指定的商品名字
        Map<String, Object> payMap = new HashMap<>();//这里是返回小程序端需要的参数
        try {
            String nonce_str = UUIDUtils.getwxPay();

            String body = goodsName;//这里是商品name需要查--------------------------------------------------------------------------------查2
            //这里需要知道价格 通过订单号查询订单获取价格
            BigDecimal bigDecimal = totalPrice;//--------------------------------------------------------------------查3
            int total_fee = bigDecimal.intValue();
            //还差sign mchid appid notify_url 放在WXPayConfig类里面
            //有几个数据经常变抽到WXPayConfig里面的 需要每一个确认 已经填好的不用改动
            Map<String, String> packgeParams = new HashMap<>();
            packgeParams.put("appid", WXPayConfig.appid);
            packgeParams.put("mch_id", WXPayConfig.mch_id);
            packgeParams.put("nonce_str", nonce_str);
            packgeParams.put("body", body);
            packgeParams.put("out_trade_no", out_trade_no);
            packgeParams.put("total_fee", total_fee + "");
            packgeParams.put("spbill_create_ip", spbill_create_ip);
            packgeParams.put("notify_url", WXPayConfig.notify_url);
            packgeParams.put("trade_type", WXPayConfig.trade_type);
            packgeParams.put("openid", openId);//这不是必要的板砖搬下来的 留着做个纪念
            String signUnfinished = PayUtils.createLinkString(packgeParams);//这里就拿到了排序后未加密的sign
            String sign = PayUtils.sign(signUnfinished, WXPayConfig.key, "utf-8");//这里就拿到了最后的需要的sign
            String xml = "<xml>" + "<appid>" + WXPayConfig.appid + "</appid>"
                    + "<body><![CDATA[" + body + "]]></body>"
                    + "<mch_id>" + WXPayConfig.mch_id + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<notify_url>" + WXPayConfig.notify_url + "</notify_url>"
                    + "<openid>" + openId + "</openid>"
                    + "<out_trade_no>" + out_trade_no + "</out_trade_no>"
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    + "<total_fee>" + total_fee + "</total_fee>"
                    + "<trade_type>" + WXPayConfig.trade_type + "</trade_type>"
                    + "<sign>" + sign + "</sign>"
                    + "</xml>";
            //上面一大片是组装了微信官方要的一个xml 后面直接发给了微信 不用管他已经拼接好了

            //----------------------------------------------------------------------------------------------------------------------以下代码直接搬的 又不能测试 能不能用会不会炸不确定
            String result = PayUtils.httpRequest(WXPayConfig.pay_url, "POST", xml);//这里调用了下单接口返回了结果
            System.out.println("\\\\\\\\\\\\\\");
            System.out.println(result);
            System.out.println(openId);
            System.out.println("\\\\\\\\\\\\\\");
            Map map = PayUtils.doXMLParse(result);
            String return_code = (String) map.get("return_code");//返回状态码
            String result_code = (String) map.get("result_code");//返回状态码
            String err_code = (String) map.get("err_code");//返回状态码
            String err_code_des = (String) map.get("err_code_des");//返回状态码

            if (return_code.equals("SUCCESS") || return_code.equals(result_code)) {
                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
                payMap.put("nonceStr", nonce_str);
                payMap.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                payMap.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
                //拼接签名需要的参数
                //大致在这一块给前端拼接了他需要的那个串 4个参数 加一个sign
                String stringSignTemp = "appId=" + WXPayConfig.appid + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id + "&signType=MD5&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = PayUtils.sign(stringSignTemp, WXPayConfig.key, "utf-8");
                //下面这个地方名字存疑 可以看看前端到底是要什么串 名字是什么----------------------------------------------------------------检查这里
                payMap.put("paySign", paySign);
            } else {
                return payMap;
            }
            //好像前端不需要这个appid如果不需要建议去掉 没意义发过去
            payMap.put("appid", WXPayConfig.appid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payMap;
    }
}
