package com.lidong.algorithm.designpattern.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 *
 * @author ls J
 * @date 2020/3/11 11:00
 */
public class MySubject implements Subject {

    /**
     * 被观察者拥有观察者属性
     */
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
