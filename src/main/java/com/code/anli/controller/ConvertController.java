package com.code.anli.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.fmc.applet.constants.ResponseState;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkTpwdCreateResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @Classhttp://gateway.kouss.com/tbpub/privilegeGetName ConvertController @Description
 * Administrator @Date 2019/11/22 13:38 @Version 1.0
 */
@RestController
@RequestMapping("/Convert")
public class ConvertController extends BaseController {
    private static Integer userId = 775420792;
    private static String url = "http://gw.api.taobao.com/router/rest";
    private static String app_key = "28149086";
    private static String secret = "62f18d2ed1337b110a1b761daa79d319";
    private static String ztk_appkey = "5354368dd94847c4be62837d3ee3525d";
    private static String ztk_sid = "23209";
    private static String ztk_pid = "mm_116453128_21774193_72742171";
    private Logger logger = Logger.getLogger(ConvertController.class.getName());

    @ResponseBody
    @PostMapping("/generalConvert")
    @CrossOrigin
    public Map<String, Object> generalConvert(@RequestBody JSONObject obj) {

        try {
            JSONObject model;
            String content = obj.getString("content");
            if (null == content) {
                return success();
            }

            //            content = "$70i3YD9laDr$";
            //            content = "$htm9YD9rEY5$";
            content = content.trim().replaceAll("(\\r\\n|\\n|\\n\\r)", "").replaceAll(" ", "");
            String tklPattern = "[^\\u4e00-\\u9fa5]\\w{11}[^\\u4e00-\\u9fa5]|";
            String urlPattern =
                    "((http[s]{0,1}|ftp)://[a-zA-Z0-9\\\\.\\\\-]+\\\\.([a-zA-Z0-9]{2,4})(:\\\\d+)?(/[a-zA-Z0-9\\\\.\\\\-~!@#$%^&*+?:_/=<>]*)?)|(www.[a-zA-Z0-9\\\\.\\\\-]+\\\\.([a-zA-Z0-9]{2,4})(:\\\\d+)?(/[a-zA-Z0-9\\\\.\\\\-~!@#$%^&*+?:_/=<>]*)?)";

            if (content.contains(".com") || content.contains(".cn") || content.contains(".net")) {
                model = urlAnalys(content);
            } else if (content.matches(tklPattern)) {
                model = tklAnalys(content);
            } else {
                return fail(ResponseState.ERROR_CONTENT_SYNTAX);
            }

            return success(model);
        } catch (Exception e) {
            e.printStackTrace();
            return fail(ResponseState.INTELLIGENT_DOOR_ERRPR);
        }
    }

    /**
     * @param content 请求参数
     * @return 返回数据
     * @throws ApiException 异常
     * @Description 淘链接解析(折淘客)
     */
    private JSONObject urlAnalys(String content) throws ApiException {

        Map<String, Object> param = new HashMap<>(5);
        param.put("appkey", ztk_appkey);
        param.put("sid", ztk_sid);
        param.put("type", 0);
        param.put("content", content);
        String post = HttpUtil.post("https://api.zhetaoke.com:10001/api/open_shangpin_id.ashx", param);
        String itemId = JSONObject.parseObject(post).getString("item_id");
        param.put("pid", ztk_pid);
        param.put("num_iid", itemId);
        param.put("signurl", 0);
        JSONObject jsonObject =
                JSONObject.parseObject(
                        HttpUtil.post("https://api.zhetaoke.com:10001/api/open_gaoyongzhuanlian.ashx", param));
        JSONObject tbkPrivilegeGetResponse = jsonObject.getJSONObject("tbk_privilege_get_response");
        JSONObject result = tbkPrivilegeGetResponse.getJSONObject("result");
        JSONObject model = result.getJSONObject("data");
        return parseTkl(model.getString("coupon_click_url"), model.getString("item_url"), model);
    }

    /**
     * @param content 请求参数
     * @return 返回数据
     * @throws ApiException 异常
     * @Description 淘口令解析(折淘客)
     */
    private JSONObject tklAnalys(String content) throws ApiException {

        Map<String, Object> param = new HashMap<>(5);
        param.put("appkey", ztk_appkey);
        param.put("sid", ztk_sid);
        param.put("pid", ztk_pid);
        param.put("tkl", content);
        String post =
                HttpUtil.post("https://api.zhetaoke.com:10001/api/open_gaoyongzhuanlian_tkl.ashx", param);
        String url = HttpUtil.get(JSONObject.parseObject(post).getString("url"));
        JSONObject model =
                JSONObject.parseObject(url)
                        .getJSONObject("tbk_privilege_get_response")
                        .getJSONObject("result")
                        .getJSONObject("data");
        return parseTkl(model.getString("coupon_click_url"), model.getString("item_url"), model);
    }

    /**
     * @param couponClickUrl 券跳转地址
     * @param clickUrl       点击跳转地址
     * @param model          参数
     * @return 返回数据
     * @throws ApiException 异常
     * @Description 淘口令生成(官方sdk)
     */
    private JSONObject parseTkl(String couponClickUrl, String clickUrl, JSONObject model)
            throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, app_key, secret);

        TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
        req.setText("更多优惠请加群:4854844");
        req.setUrl(StringUtils.isEmpty(couponClickUrl) ? clickUrl : couponClickUrl);
        req.setLogo("https://gw.alicdn.com/tfs/TB1KR2agBv0gK0jSZKbXXbK2FXa-800-450.jpg");
        req.setExt("{}");
        TbkTpwdCreateResponse rsp = client.execute(req);
        String body = rsp.getBody();
        JSONObject resultObj = JSONObject.parseObject(body);
        JSONObject resu = resultObj.getJSONObject("tbk_tpwd_create_response").getJSONObject("data");
        String scan = resu.getString("model");
        logger.info("生成的口令: " + scan);
        model.put("scan", scan);
        return model;
    }
}
