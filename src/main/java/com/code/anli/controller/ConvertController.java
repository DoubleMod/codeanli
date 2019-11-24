package com.code.anli.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkTpwdCreateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classhttp://gateway.kouss.com/tbpub/privilegeGetName ConvertController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/11/22 13:38
 * @Version 1.0
 **/

@RestController
@RequestMapping("/Convert")
public class ConvertController extends BaseController {
    private Logger logger = Logger.getLogger(ConvertController.class.getName());

    private static Integer userId = 775420792;
    private static String url = "http://gw.api.taobao.com/router/rest";
    private static String app_key = "28149086";
    private static String appkey = "MskSwLnnDS";
    private static Integer adzone_id = 72992585;
    private static Integer site_id = 21798822;
    private static String secret = "62f18d2ed1337b110a1b761daa79d319";
    private static String sessionKey = "70000100133174d222fdfcbca97a3869a14590009be1b1c44eeeba18cc0f395edc852c0775420792";
    private static String session = "700001015456275a28a99d5dda74daa8171d3eb8644093d154515de541d868e0d45e985775420792";


    @ResponseBody
    @PostMapping("/generalConvert")
    @CrossOrigin
    public Map generalConvert(@RequestBody JSONObject obj) {

        try {
            TaobaoClient client = new DefaultTaobaoClient(url, app_key, secret);

            String content = obj.getString("content");
            if (null == content) {
                return success();
            }

            Map<String, Object> model = new HashMap<>(16);
            model.put("item_id", content);
            model.put("site_id", site_id);
            model.put("adzone_id", adzone_id);
            model.put("session", session);
            JSONObject resultObject = JSONObject.parseObject(HttpUtil.post("http://gateway.kouss.com/tbpub/", model));
            System.out.println(resultObject.toJSONString());

            TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
            req.setText("更多优惠请加群:4854844");
            JSONObject result = resultObject.getJSONObject("result").getJSONObject("data");
            String couponClickUrl = result.getString("coupon_click_url");
            req.setUrl(couponClickUrl);
            req.setLogo("https://gw.alicdn.com/tfs/TB1KR2agBv0gK0jSZKbXXbK2FXa-800-450.jpg");
            req.setExt("{}");
            TbkTpwdCreateResponse rsp = client.execute(req);
            String body = rsp.getBody();
            JSONObject object = JSONObject.parseObject(body);
            JSONObject response = object.getJSONObject("tbk_tpwd_create_response").getJSONObject("data");
            String scan = response.getString("model");
            logger.info("生成的口令: " + scan);

            resultObject.put("scan", scan);

            return success(resultObject);
        } catch (ApiException e) {
            e.printStackTrace();
            return fail(com.fmc.applet.constants.ResponseState.INTELLIGENT_DOOR_ERRPR);
        }

    }


    /**
     * 根据宝贝URL截取宝贝ID
     * http://auction1.taobao.com/auction/item_detail-0db1-d57b90f4c406fe1ee1517884dafe338b.jhtml
     * 截取后为32位的字符串：d57b90f4c406fe1ee1517884dafe338b
     * http://item.taobao.com/auction/item_detail.htm?itemID=1b70b4c3fb32cf0e24af9a649ad5360d
     * 截取后为32位的字符串：1b70b4c3fb32cf0e24af9a649ad5360d
     * 如下几种宝贝URL均合法：
     * http://item.taobao.com/auction/item_detail-0db2-a159acabbeedeb61ea92231371adae67.jhtml
     * http://auction1.taobao.com/auction/item_detail-0db1-6ce724f828e554364f6bb8cd4fdf0249.jhtml
     * http://item.taobao.com/auction/item_detail.htm?itemID=1b70b4c3fb32cf0e24af9a649ad5360d&ali_refid=a3_419095_1006:380074963:6:%B7%DB:504f81b2bd89eb72144729a403c22c10
     * http://item.taobao.com/auction/item_detail.jhtml?item_id=f19580fd3d5ec2395ff6e7d4192b9230&x_id=0db1
     * http://item.taobao.com/auction/item_detail--2c8338b253d3beaa41afb51f610e2eb5.jhtml
     *
     * @param auctionUrl
     * @return
     */

    @Test
    public void parseUrl() {
        String auctionUrl = "https://detail.tmall.com/item.htm?id=574397159757&ali_trackid=2:mm_128214214_40172838_152876257:1574524787_126_684367570&spm=a2e2e.10720394/nn.90200100.1&pvid=23399863&ak=23399863";
        String ret = "";
//        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        try {
            String regex1 = "http://(item|auction1)\\.taobao\\.com/auction/item_detail-(\\w{4}|\\w{3})-(\\w{32})\\.jhtml.*";
            String regex2 = "http://(item|auction1)\\.taobao\\.com/auction/item_detail\\.(htm|jhtml)\\?(itemID|item_id)=(\\w{32}).*";
            String regex3 = "http://item.taobao.com/auction/item_detail--(\\w{32}).jhtml";
            Pattern pattern1 = Pattern.compile(regex1, Pattern.CASE_INSENSITIVE);
            Matcher matcher1 = pattern1.matcher(auctionUrl);

            Pattern pattern2 = Pattern.compile(regex2, Pattern.CASE_INSENSITIVE);
            Matcher matcher2 = pattern2.matcher(auctionUrl);

            Pattern pattern3 = Pattern.compile(regex3, Pattern.CASE_INSENSITIVE);
            Matcher matcher3 = pattern3.matcher(auctionUrl);

            if (matcher1.matches()) {
                ret = matcher1.group(3);
                System.out.println(ret);
            } else if (matcher2.matches()) {
                ret = matcher2.group(4);
                System.out.println(ret);
            } else if (matcher3.matches()) {
                ret = matcher3.group(1);
                System.out.println(ret);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(ret);
    }
}
