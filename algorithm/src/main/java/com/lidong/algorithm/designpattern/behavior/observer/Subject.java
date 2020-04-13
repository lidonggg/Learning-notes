package com.lidong.algorithm.designpattern.behavior.observer;

/**
 * @author ls J
 * @date 2020/3/11 10:59
 */
public interface Subject {

    /**
     * register
     *
     * @param observer observer
     */
    void registerObserver(Observer observer);

    /**
     * remove
     *
     * @param observer observer
     */
    void removeObserver(Observer observer);

    /**
     * nbotify
     *
     * @param message message
     */
    void notifyObservers(Object message);
}
