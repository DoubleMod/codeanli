package com.code.anli.mq;

import com.alibaba.fastjson.JSONObject;
import com.code.anli.config.RabbitMQDirectConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * direct模式生产者
 *
 * @author cm_wang
 */
@Component
public class DirectSender {
    private Logger logger = Logger.getLogger(DirectSender.class.getName());

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDirect(JSONObject obj) {

//        logger.info("sendDirectQueue已发送消息");
        // 第一个参数是指要发送到哪个队列里面， 第二个参数是指要发送的内容
        this.amqpTemplate.convertAndSend(RabbitMQDirectConfig.DIRECT_QUEUE, obj);
    }
}
