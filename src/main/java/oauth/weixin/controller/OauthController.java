package oauth.weixin.controller;

import oauth.weixin.service.iface.IWeixinService;
import oauth.weixin.util.Result;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lichenyi
 * @date 2017-7-6
 */
@Controller
@RequestMapping("/oauth")
public class OauthController {
    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private IWeixinService weixinService;

    @Value("${weixin.open.connect}")
    private String connectUrl;

    /**
     * 跳转到微信页面
     * @param response
     * @return
     */
    @GetMapping("/weixin/login")
    @ResponseBody
    public void index(HttpServletResponse response){
        try {
            logger.info(String.format(" connectUrl --> %s ", connectUrl));
            response.sendRedirect(connectUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取微信用户信息
     * @param code
     * @return
     */
    @GetMapping("/weixin/userInfo")
    @ResponseBody
    public ResponseEntity<Result> getWeixinUserInfo(@RequestParam(value="code") String code){
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(weixinService.getUserInfo(code)));
    }
}
