package oauth.weixin.service.iface;

import com.alibaba.fastjson.JSONObject;

/**
 * @author lichenyi
 * @date 2017-7-5
 */
public interface IWeixinService {
    JSONObject getUserInfo(String code);
}
