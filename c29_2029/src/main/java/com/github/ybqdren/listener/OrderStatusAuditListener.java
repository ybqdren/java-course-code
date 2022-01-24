package com.github.ybqdren.listener;

import com.github.ybqdren.entity.ExOrderOfRecharge;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/24 16:53
 * @package com.github.ybqdren.listener
 * @description
 * 创建一个类去监听订单状态变更，然后通知相应的逻辑代码各自去干活 （监听订单状态变更）
 **/

@Slf4j
public class OrderStatusAuditListener {
    @PostPersist
    private void postPersist(ExOrderOfRecharge orderOfRecharge){
       log.info("订单" + orderOfRecharge.getId() +"更新了");
    }

    @PostRemove
    private void postRemove(ExOrderOfRecharge orderOfRecharge){
        log.info("订单" + orderOfRecharge.getId() + "被删除");
    }

    @PostUpdate
    private void postUpdate(ExOrderOfRecharge orderOfRecharge){
        log.info("订单" + orderOfRecharge.getId() + "被更新");
    }

}
