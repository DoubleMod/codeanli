package com.code.anli.mq;

import com.alibaba.fastjson.JSONObject;
import com.code.anli.config.RabbitMQTopicConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * topic模式生产者
 *
 * @author cm_wang
 */
@Component
public class TopicSender {

    private Logger logger = Logger.getLogger(TopicSender.class.getName());

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * topic 模式
     */
    public void sendTopic(JSONObject obj) {

        logger.info("sendTopic已发送消息");
        // 第一个参数：TopicExchange名字
        // 第二个参数：Route-Key
        // 第三个参数：要发送的内容
        this.amqpTemplate.convertAndSend(RabbitMQTopicConfig.TOPIC_EXCHANGE, "rabbitmq.message", obj);
    }
}
