package com.lidong.algorithm.designpattern.behavior.observer;

/**
 * 观察者2
 *
 * @author ls J
 * @date 2020/3/11 11:05
 */
public class MyObserverTwo implements Observer {
    @Override
    public void update(Object message) {
        System.out.println("observer two get message");
    }
}
