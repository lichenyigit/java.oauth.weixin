package oauth.weixin.service.impl;

import com.alibaba.fastjson.JSONObject;
import oauth.weixin.service.iface.IWeixinService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

/**
 * @author Lichenyi
 * @date 2017-7-5
 */
@SuppressWarnings("all")
@Service("IWeixinService")
public class WeixinService implements IWeixinService {
    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weixin.open.connect}")
    private String weixin_open_connect;//网站跳转
    @Value("${weixin.open.sns.oauth2}")
    private String weixin_open_sns_oauth2;//获取access token
    @Value("${weixin.open.sns.userinfo}")
    private String weixin_open_sns_userinfo;//根据access token 获取用户信息

    /**
     * 获取access_token
     *
     * @param code
     * @return
     */
    private JSONObject getAccessToken(String code) {
        String tokenJson = null;
        String url = String.format(weixin_open_sns_oauth2, code);
        String resultStr = restTemplate.getForObject(url, String.class);
        JSONObject result = JSONObject.parseObject(resultStr);
        if (result.getString("access_token") != null) {
            return result;
        }
        logger.error(String.format("获取accesstoken错误  %s", result.toJSONString()));
        return result;
    }

    /**
     * 获取用户信息
     *
     * @param code
     * @return
     */
    @Override
    public JSONObject getUserInfo(String code) {
        try {
            JSONObject accessToken = getAccessToken(code);
            if (accessToken.getString("access_token") == null) {
                return accessToken;
            }
            String token = accessToken.getString("access_token");
            String openid = accessToken.getString("openid");
            String url = String.format(weixin_open_sns_userinfo, token, openid);
            String resultStr = restTemplate.getForObject(url, String.class);
            resultStr = new String(resultStr.getBytes("ISO-8859-1"), "UTF-8");
            JSONObject result = JSONObject.parseObject(resultStr);
            if (result.getString("openid") != null) {
                return result;
            }
            logger.error(String.format("获取用户信息错误  %s", result.toJSONString()));
            return result;
        }catch (UnsupportedEncodingException e){
            logger.error(String.format("转码错误  %s", e.getMessage()));
            e.printStackTrace();
        }
        return null;
    }

}