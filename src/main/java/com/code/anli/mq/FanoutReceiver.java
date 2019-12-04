package com.code.anli.mq;

import com.alibaba.fastjson.JSONObject;
import com.code.anli.config.RabbitMQFanoutConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * fanout模式消费者
 *
 * @author cm_wang
 */
@Component
public class FanoutReceiver {
    private Logger logger = Logger.getLogger(FanoutReceiver.class.getName());

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQFanoutConfig.FANOUT_QUEUE)
    public void receiveFanout(JSONObject obj) {
        logger.info("receiveFanout收到消息" + obj.toString());
    }
}
