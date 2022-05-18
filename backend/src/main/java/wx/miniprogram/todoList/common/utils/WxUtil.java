package wx.miniprogram.todoList.common.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 跟微信相关的一些工具
 * @author hbc
 */

public class WxUtil {

    private final static String appId = "wx658215081ce9ffad";
    private final static String secretId = "5021bd5bf45c2c799ed132e41b0af621";

    /**
     * 根据code前往微信服务器获取唯一openId
     * @param code
     * @return openId
     */
    public static String getOpenId(String code) {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 参数
        StringBuffer params = new StringBuffer();
        try {
            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
            params.append("appid=" + URLEncoder.encode(appId, "utf-8"));
            params.append("&");
            params.append("secret="+secretId);
            params.append("&");
            params.append("js_code=" + URLEncoder.encode(code, "utf-8"));
            params.append("&");
            params.append("grant_type=authorization_code");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        // 创建Post请求
        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/sns/jscode2session" + "?" + params);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            // 解析openId,实现登录
            String result = EntityUtils.toString(responseEntity);
            Map mapType = JSON.parseObject(result,Map.class);
            return (String)mapType.get("openid");
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
