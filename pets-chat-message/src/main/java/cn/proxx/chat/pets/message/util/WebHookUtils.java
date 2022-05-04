package cn.proxx.chat.pets.message.util;

import com.xuexiang.xutil.net.JsonUtil;
import com.xuexiang.xutil.security.EncodeUtils;

import java.net.URLEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import cn.proxx.chat.pets.entity.vo.WebHookBodyVO;
import cn.proxx.chat.pets.entity.vo.WebHookTextVO;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public final class WebHookUtils {

    private WebHookUtils() {
    }

    public static void sendDingDingText(String baseUrl, String secret, String content, Callback respCallback) {
        // 加签
        Long timestamp = System.currentTimeMillis();
        String sign = "";
        try {
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            sign = URLEncoder.encode(EncodeUtils.base64Encode2String(signData), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 请求体
        WebHookBodyVO bodyJson = new WebHookBodyVO();
        WebHookTextVO textVO = new WebHookTextVO();
        textVO.setContent(content);
        bodyJson.setText(textVO);
        bodyJson.setMsgtype("text");
        // 请求
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, JsonUtil.toJson(bodyJson));
        Request request = new Request.Builder()
                .url(baseUrl + "&timestamp=" + timestamp + "&sign=" + sign)
                .post(body)
                .build();
        client.newCall(request).enqueue(respCallback);
    }
}
