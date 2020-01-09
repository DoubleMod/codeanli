package com.code.anli.controller;

import com.alibaba.fastjson.JSONObject;
import com.code.anli.mq.DirectSender;
import com.code.anli.mq.FanoutSender;
import com.code.anli.mq.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 消息路由规则：四种模式（topic、direct、fanout、header） topic：根据绑定关键字通配符规则匹配、比较灵活
 * direct：默认，根据routingKey完全匹配，好处是先匹配再发送 fanout：不需要指定routingkey，相当于群发 header：不太常用，可以自定义匹配规则
 *
 * @author cm_wang
 */
@RestController
@RequestMapping("/api/v1")
public class MessageController extends BaseController {

    @Autowired
    private TopicSender topicSender;

    @Autowired
    private DirectSender directSender;

    @Autowired
    private FanoutSender fanoutSender;

    /**
     * topic 模式
     *
     * @return 返回数据
     */
    @GetMapping("/sendTopic")
    public Map<String, Object> sendTopic(@RequestParam("obj") JSONObject obj) {
        topicSender.sendTopic(obj);
        return success();
    }

    /**
     * direct 模式 <strong>发送者与接收者的Queue名字一定要相同，否则接收收不到消息</strong>
     *
     * @return 返回数据
     */
    @GetMapping("/sendDirect")
    public Map<String, Object> sendDirect(@RequestParam("obj") JSONObject obj) {
        for (int i = 0; i < 10; i++) {
            directSender.sendDirect(obj);
        }
        return success();
    }

    /**
     * fanout模式
     *
     * @return 返回数据
     */
    @GetMapping("/sendFanout")
    public Map<String, Object> sendFanout(@RequestParam("obj") JSONObject obj) {
        for (int i = 0; i < 10; i++) {
            fanoutSender.sendFanout(obj);
        }
        return success();
    }
}
