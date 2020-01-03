package com.code.anli.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.code.anli.entity.TOrder;
import com.code.anli.mq.DirectSender;
import com.code.anli.service.ITOrderService;
import com.fmc.applet.constants.ResponseState;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classhttp://gateway.kouss.com/tbpub/privilegeGetName QueryOrderController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/11/27 22:38
 * @Version 1.0
 **/

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
    private Logger logger = Logger.getLogger(OrderController.class.getName());

    private static String ztk_appkey = "5354368dd94847c4be62837d3ee3525d";
    private static String ztk_sid = "23209";
    private static String ztk_pid = "mm_116453128_21774193_72742171";

    @Autowired
    private DirectSender directSender;
    @Autowired
    private ITOrderService orderService;


    @CrossOrigin
    @ResponseBody
    @PostMapping("/queryOrder")
    public Map queryOrder(@RequestBody JSONObject obj) {
        Date nowDate = new Date();
        Map<String, Object> parm = new HashMap<>();
        parm.put("appkey", ztk_appkey);
        parm.put("sid", ztk_sid);

        try {
            JSONArray result;
            String dateString = DateUtil.formatDateTime(DateUtil.offsetHour(nowDate, -3));
            parm.put("start_time", null == obj.getString("start_time") ? dateString : obj.getString("start_time"));
            parm.put("end_time", null == obj.getString("end_time") ? DateUtil.formatDateTime(nowDate) : obj.getString("end_time"));
            String resultString = HttpUtil.post("https://api.zhetaoke.com:10001/api/open_dingdanchaxun2.ashx", parm);
            JSONObject resultObject = JSONObject.parseObject(resultString);
            String url = resultObject.getString("url");
            System.out.println(url);
            String s = HttpUtil.get(url);
            JSONObject jsonObject = JSONObject.parseObject(s);
            if (jsonObject.containsKey("error_response")) {
                List<TOrder> tOrders = orderService.selectList(new EntityWrapper<>());
                result = JSONArray.parseArray(JSONObject.toJSONString(tOrders));
                return success(result);
            }
            JSONObject object = jsonObject.getJSONObject("tbk_sc_order_details_get_response");
            JSONObject data = object.getJSONObject("data");
            JSONObject results = data.getJSONObject("results");
            result = results.getJSONArray("publisher_order_dto");

            if (result != null) {
                JSONObject model = new JSONObject();
                model.put("orders", result);
                directSender.sendDirect(model);
            } else {
                List<TOrder> tOrders = orderService.selectList(new EntityWrapper<>());
                result = JSONArray.parseArray(JSONObject.toJSONString(tOrders));
            }


            return success(result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getCause().getMessage());
            return fail(com.fmc.applet.constants.ResponseState.INTELLIGENT_DOOR_ERRPR);
        }


    }
}
