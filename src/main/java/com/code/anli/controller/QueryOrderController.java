package com.code.anli.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @Classhttp://gateway.kouss.com/tbpub/privilegeGetName QueryOrderController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/11/27 22:38
 * @Version 1.0
 **/

@RestController
@RequestMapping("/Query")
public class QueryOrderController extends BaseController {
    private Logger logger = Logger.getLogger(QueryOrderController.class.getName());

    private static String ztk_appkey = "5354368dd94847c4be62837d3ee3525d";
    private static String ztk_sid = "23209";
    private static String ztk_pid = "mm_116453128_21774193_72742171";

    @CrossOrigin
    @ResponseBody
    @PostMapping("/queryOrder")
    public Map generalConvert(@RequestBody JSONObject obj) {

        try {

            Map<String, Object> parm = new HashMap<>();
            parm.put("appkey", ztk_appkey);
            parm.put("sid", ztk_sid);
            Date nowDate = new Date();
            String dateString = DateUtil.formatDateTime(DateUtil.offsetHour(nowDate, -3));
            parm.put("start_time", null == obj.getString("start_time") ? dateString : obj.getString("start_time"));
            parm.put("end_time", null == obj.getString("end_time") ? DateUtil.formatDateTime(nowDate) : obj.getString("end_time"));
            String resultString = HttpUtil.post("https://api.zhetaoke.com:10001/api/open_dingdanchaxun2.ashx", parm);
            JSONObject resultObject = JSONObject.parseObject(resultString);
            String url = resultObject.getString("url");
            System.out.println(url);
            String s = HttpUtil.get(url);
            JSONObject jsonObject = JSONObject.parseObject(s);
            JSONObject object = jsonObject.getJSONObject("tbk_sc_order_details_get_response");
            JSONObject data = object.getJSONObject("data");
            JSONObject results = data.getJSONObject("results");
            JSONArray model = results.getJSONArray("publisher_order_dto");
            return success(model);
        } catch (Exception e) {
            e.printStackTrace();
            return fail(com.fmc.applet.constants.ResponseState.INTELLIGENT_DOOR_ERRPR);
        }

    }
}
