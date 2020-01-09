package com.code.anli.mq;

import com.alibaba.fastjson.JSONObject;
import com.code.anli.config.RabbitMQTopicConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * topic模式消费者
 *
 * @author cm_wang
 */
@Component
public class TopicReceiver {
    private Logger logger = Logger.getLogger(TopicReceiver.class.getName());

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQTopicConfig.TOPIC_QUEUE)
    public void receiveTopic(JSONObject obj) {
        logger.info("receiveTopic收到消息:" + obj.toString());
    }
}
