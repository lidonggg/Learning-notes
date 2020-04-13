package com.lidong.algorithm.designpattern.behavior.observer;

/**
 * @author ls J
 * @date 2020/3/11 11:06
 * 观察者模式常用的模板，但是根据不同的场景有着可能完全不一样的实现，但是万变不离其宗
 */
public class Test {

    public static void main(String[] args) {
        MySubject mySubject = new MySubject();
        mySubject.registerObserver(new MyObserverOne());
        mySubject.registerObserver(new MyObserverTwo());
        mySubject.notifyObservers(new Object());
    }
}
