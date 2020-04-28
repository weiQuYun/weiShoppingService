package com.wqy.wx.back.common.util;


import org.apache.commons.codec.digest.DigestUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * 整个Utils类只做了简单的识别判断 没有细看 逻辑目测没有问题 需要测试数次验证
 * 比较核心的代码已经标注 重点关注
 * **/

public class PayUtils {
    public static String createLinkString(Map<String, String> params) {
        //拿取keyset集合排序 这是为微信前面要求所有参数排序 改成 key=value&key=value  这个格式写的
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }
    //-----------------------------------------------------------------------------------------------------------------------------核心
    public static String sign(String signUnfinished, String key, String input_charset) {
        signUnfinished = signUnfinished + "&key=" + key;
        return DigestUtils.md5Hex(getContentBytes(signUnfinished, input_charset)).toUpperCase();
    }
    //这个方法是首次MD5加密获取sign的那个 目测逻辑无异常
    public static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    /**
     * @param requestUrl    请求地址
     * @param requestMethod 请求方法
     * @param outputStr     参数
     */
    //-----------------------------------------------------------------------------------------------------------------------------核心
    public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
        // 创建SSLContext                         //核心支付代码
        StringBuffer buffer = null;
        try {
            URL url = new URL(requestUrl);              //将支付url生成
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(requestMethod);
            conn.setDoOutput(true);///
            conn.setDoInput(true);///
            conn.connect();
            //往服务器端写内容
            if (null != outputStr) {
                OutputStream os = conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }
            // 读取服务器端返回的内容
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
    public static InputStream String2Inputstream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    //-----------------------------------------------------------------------------------------------------------------------------核心
    public static Map doXMLParse(String strxml) throws Exception {
        if (null == strxml || "".equals(strxml)) {
            return null;
        }
        //这个方法大致是说将微信发回来的xml给他解析成map
        Map m = new HashMap();
        InputStream in = String2Inputstream(strxml);
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }

            m.put(k, v);
        }

        //关闭流
        in.close();

        return m;
    }
    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<String, String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        //这个方法看起来是去掉空值的 看不到微信返回值 不能定能否能用
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }


    /**
     * 签名字符串
     *
     * @param text          需要签名的字符串
     * @param sign          签名结果
     * @param key           密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    //-----------------------------------------------------------------------------------------------------------------------------核心
    public static boolean verify(String text, String sign, String key, String input_charset) {
        text = text +"&key="+ key;
        String mysign = DigestUtils.md5Hex((getContentBytes(text, input_charset))).toUpperCase();
        if (mysign.equals(sign)) {
            return true;
        } else {
            return false;
        }
        //这个方法是定支付是否成功的很重要 如果为 true 发回报文 那么 钱就支付过来了
    }




    //这个没注意看
    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }
}
