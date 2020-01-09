package com.code.anli.mq;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.code.anli.config.RabbitMQDirectConfig;
import com.code.anli.constant.Const;
import com.code.anli.controller.OrderController;
import com.code.anli.entity.DirectParamModel;
import com.code.anli.entity.TOrder;
import com.code.anli.service.ITOrderService;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * direct模式消费者
 *
 * @author cm_wang
 */
@Component
public class DirectReceiver {
    private Logger logger = Logger.getLogger(DirectReceiver.class.getName());

    @Autowired
    private ITOrderService orderService;

    @Autowired
    private OrderController orderController = new OrderController();

    @Autowired
    private DirectSender directSender;

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitMQDirectConfig.DIRECT_QUEUE)
    public void receiverDirect(JSONObject obj) {
        DirectParamModel directParamModel =
                JSONObject.parseObject(obj.toJSONString(), DirectParamModel.class);
        List<TOrder> orders = directParamModel.getOrders();
        orderProcess(orders);
        //        logger.info("处理完毕");
    }

    //    0 * * * * *
    //    0 0 * * * ?
    @Scheduled(cron = "0 30 * * * ?")
    public void shcsd() {
        JSONObject object = new JSONObject();
        object.put("json", new JSONObject());
        object.put("orders", new ArrayList<>());
        object.put("wewe", 123);
        directSender.sendDirect(object);

        Map map = orderController.queryOrder(new JSONObject());
        if (Const.TWOHUNDRED.equals(map.get("state"))) {
            logger.info("定时任务执行成功");
        } else {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error(e.getCause().getMessage());
            }
            map = orderController.queryOrder(new JSONObject());
            if (Const.TWOHUNDRED.equals(map.get("state"))) {
                logger.info("定时任务执行成功");
            } else {
                logger.info("定时任务执行shi失败");
            }
        }
    }

    private void orderProcess(List<TOrder> orders) {
        if (null != orders) {
            for (TOrder order : orders) {
                String tradeParentId = order.getTradeParentId();
                int count =
                        orderService.selectCount(
                                new EntityWrapper<TOrder>()
                                        .where("trade_parent_id={0}", tradeParentId)
                                        .and("trade_id={0}", order.getTradeId()));
                if (count == 0) {
                    orderService.insert(order);
                    logger.info("成功插入1条,trade:" + order.getTradeParentId());
                }
            }
        }
    }
}
