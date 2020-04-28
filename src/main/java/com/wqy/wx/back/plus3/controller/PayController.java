package com.wqy.wx.back.plus3.controller;


import com.wqy.wx.back.common.util.PayUtils;
import com.wqy.wx.back.configer.WXPayConfig;
import com.wqy.wx.back.plus3.mapper.ShMemberMapper;
import com.wqy.wx.back.plus3.service.IShMemberService;
import com.wqy.wx.back.plus3.service.WXPayService;
import com.wqy.wx.back.plus3.service.impl.WXPayServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
@Api(tags = "微信支付接口")
@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private WXPayServiceImpl wxPayService;
    @Autowired
    private IShMemberService shMemberService;
    /**
     * 必要字段
     * appid:微信小程序ID
     * mch_id:微信商户号ID
     * nonce_str:随机字符串
     *sign:签名:需要MD5加密
     * body:商品描述
     * out_trade_no:商户订单号即我们自己服务器的订单号
     * total_fee:订单金额 需要int
     * spbill_create_ip:终端IP     √
     * notify_url:回调函数位置 后端接口
     * trade_type:JSAPI  默认值    √
     * **/

    /**
     * 微信支付service
     * by lcx
     * 1.获取用户IP openId OrderId(商户唯一)
     * **/


    //这是微信支付前端发起请求首次请求接口 这个的返回值就是他要发的 数据 他发送数据后 微信会回调下面那个方法 告诉后端支付是否成功
    //如果成功 我们发回报文 即可完成支付 失败也要发报文!
    @ApiOperation("首次请求")
    @PostMapping(value = "/wxPay")
    public Object wxPay(String orderId, HttpServletRequest request,String id){
        System.out.println("555555555555555");
        Object result = new Object();
        //1.获取用户IP
        try {
            //获取用户的openID------------------------------------------------------------------需要加这里使用本地shmemberMapper查出来不要用前台发
            String openId = shMemberService.selectById(id).getOpenid();
            //获取用户的ip 命名这里用的微信需要的字段名-------------------------------------------后一字段
            String spbill_create_ip = getIpAddr(request);
            System.out.println(spbill_create_ip);
            result = wxPayService.wxPay(spbill_create_ip,orderId,openId);

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("========");
        System.out.println(result);
        return result;
    }
    @ApiOperation("前台发送完数据后回调这个")
    @PostMapping("/wxNotify")
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";

        Map map = PayUtils.doXMLParse(notityXml);

        String returnCode = (String) map.get("return_code");
        if ("SUCCESS".equals(returnCode)) {
            //验证签名是否正确
            Map<String, String> validParams = PayUtils.paraFilter(map);  //回调验签时需要去除sign和空值参数
            String prestr = PayUtils.createLinkString(validParams);
            if (PayUtils.verify(prestr, (String) map.get("sign"), WXPayConfig.key, "utf-8")) {

               //---------------------------------------------------------------------------------
                /**此处添加自己的业务逻辑代码start**/
                //注意要判断微信支付重复回调，支付成功后微信会重复的进行回调
                /**此处添加自己的业务逻辑代码end**/
                //暂定这个用于vip购买及商品购买查验 由于不能测试 暂时没有写 需要客户给与上方三个缺省数据 测试后 增加
                //---------------------------------------------------------------------------------


                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            }
        }else {
            //支付失败的意思
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }
    /**
     * 这个method用于获取ID 之后会进行测试  --------------------------------------------------------------------------测试1
     * **/
    private String getIpAddr(HttpServletRequest request) {
       /* Enumeration<String> headerNames=request.getHeaderNames();
        for(Enumeration<String> e = headerNames; e.hasMoreElements();){
        String thisName=e.nextElement().toString();
        String thisValue=request.getHeader(thisName);
        System.out.println("header的key:"+thisName+"--------------header的value:"+thisValue);
        }*/
       /* String ip = request.getHeader("X-Forwarded-For");//这里不确定是不是这个key 暂定这个不改
        System.out.println(ip);
        if (StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }*/
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        return ip;
    }
    }
