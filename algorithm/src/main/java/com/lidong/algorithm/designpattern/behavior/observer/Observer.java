package com.lidong.algorithm.designpattern.behavior.observer;

/**
 * @author ls J
 * @date 2020/3/11 10:59
 */
public interface Observer {

    /**
     * 获取消息通知，执行自己的逻辑
     *
     * @param message message
     */
    void update(Object message);
}
