package com.github.ybqdre.observer;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/29 12:40
 * @package com.github.ybqdre.observer
 * @description
 * 抖音主播
 * 粉丝观察主播
 **/
public abstract class AbstractTikToker {
    // 添加粉丝
    abstract void addFans(AbstractFans fans);

    // 通知粉丝
    abstract void notifyFans(String msg);
}
