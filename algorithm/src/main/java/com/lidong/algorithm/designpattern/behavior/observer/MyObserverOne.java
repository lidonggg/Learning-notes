package com.lidong.algorithm.designpattern.behavior.observer;

/**
 * @author ls J
 * @date 2020/3/11 11:01
 * 观察者1
 */
public class MyObserverOne implements Observer {
    @Override
    public void update(Object message) {
        System.out.println("observe 1 get message");
    }
}
