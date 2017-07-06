# oauth.weixin
微信第三封登陆

项目启动后首先
1.在浏览器输入：localhost:8888/oauth/weixin/index
然后扫码进入，
2.复制url地址后的code放到code后
http://localhost:8888/oauth/weixin/userInfo?code=
成功返回：

{
    
    "errorCode": 0,
    "errorDescription": "success",
    "requestId": "a9026ed2-2986-434c-8762-88deb14cb54d",
    "result": {
        "country": "CN",
        "unionid": "oV6Xy0rmk13eJp_TCOHKOKcbzGFI",
        "province": "Henan",
        "city": "Luoyang",
        "openid": "oe9Uv01iSHLbUR439JUY-nT3Rpbg",
        "sex": 1,
        "nickname": "一壶酒",
        "headimgurl": "http://wx.qlogo.cn/mmopen/muJQmcibTZam2heIDTXUseWtwyxiarxFXtACucoib1w5PibiaDun7EJibw6ibfC0z1XxSpmjmickKK0Ms2nwCOezy9WYLp14rH1RhjEib/0",
        "language": "zh_CN",
        "privilege": []
    }
}

