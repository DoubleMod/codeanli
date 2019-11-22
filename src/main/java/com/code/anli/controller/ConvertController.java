package com.code.anli.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.fmc.applet.constants.ResponseState;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkCouponGetRequest;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkCouponGetResponse;
import com.taobao.api.response.TbkTpwdCreateResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @Classhttp://gateway.kouss.com/tbpub/privilegeGetName ConvertController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/11/22 13:38
 * @Version 1.0
 **/

@RestController
@RequestMapping("/Convert")
public class ConvertController extends BaseController{
    private Logger logger = Logger.getLogger(ConvertController.class.getName());

    private static Integer userId = 775420792;
    private static String url = "http://gw.api.taobao.com/router/rest";
    private static String appkey = "28149086";
    private static String adzone_id = "72992585";
    private static String site_id = "21798822";
    private static String secret = "62f18d2ed1337b110a1b761daa79d319";
    private static String sessionKey = "70000100133174d222fdfcbca97a3869a14590009be1b1c44eeeba18cc0f395edc852c0775420792"; // 必须替换为沙箱账号授权得到的真实有效SessionKey


    @ResponseBody
    @RequestMapping("/generalConvert")
    public Map  generalConvert(@RequestBody JSONObject parm) {

        try {
            TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
            TbkItemClickExtractRequest req = new TbkItemClickExtractRequest();
            req.setClickUrl("https://s.click.taobao.com/***");
            TbkItemClickExtractResponse rsp = client.execute(req);
            System.out.println(rsp.getBody());

            Map<String,Object> mop = new HashMap<>(5);
            mop.put("apikey", "MskSwLnnDS");
            mop.put("tkl","￥hRCeYw6jbYb￥" );
            String post = HttpUtil.post(" https://api.taokouling.com/tkl/tkljm", mop);
            String url = JSONObject.parseObject(post).getString("url");
            if (null == url) {
                return success("口令无效");
            }

            String keyword = parm.getString("keyword");
            if (keyword == null) {
                return success(ResponseState.ERROR_PARAM);
            }

            Map<String, Object> model = new HashMap<>();
            model.put("session", sessionKey);
            model.put("adzone_id", adzone_id);
            model.put("site_id", site_id);
            model.put("password_content", keyword);
            String post1 = HttpUtil.post("http://gateway.kouss.com/tbpub/tpwdConvert", model);
            JSONObject result1 = JSONObject.parseObject(post1).getJSONObject("data");
            if (result1 == null) {
                logger.info("口令异常");
                return success("口令异常");
            }
            String itemId = result1.getString("num_iid");
            model.put("item_id", itemId);//"6009971748644"
            String post2 = HttpUtil.post("http://gateway.kouss.com/tbpub/privilegeGet", model);
            JSONObject result2 = JSONObject.parseObject(post2).getJSONObject("result");
            if (result2 == null) {
                logger.info("商品异常");
                return success("商品异常");
            }
            JSONObject jsonObject = result2.getJSONObject("data");

            TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
            req.setText("更多优惠请加群:4854844");
            req.setUrl(jsonObject.getString("coupon_click_url"));
            req.setLogo("https://gw.alicdn.com/tfs/TB1KR2agBv0gK0jSZKbXXbK2FXa-800-450.jpg");
            req.setExt("{}");
            TbkTpwdCreateResponse rsp = client.execute(req);
            String body = rsp.getBody();
            JSONObject obj = JSONObject.parseObject(body);
            JSONObject response = obj.getJSONObject("tbk_tpwd_create_response");
            JSONObject data = response.getJSONObject("data");
            String momap = data.getString("model");
            logger.info("生成的口令: "+momap);

            return success(momap);

        } catch (ApiException e) {
            e.printStackTrace();
            return fail(ResponseState.INTELLIGENT_DOOR_ERRPR);
        }



    }

}
