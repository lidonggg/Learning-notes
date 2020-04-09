package com.lidong.algorithm.designpattern.behavior.visitor;

/**
 * @author ls J
 * @date 2020/4/8 11:00
 */
public abstract class BaseObj {

    public BaseObj() {

    }

    /**
     * accept operate
     *
     * @param visitor visitor
     */
    abstract public void accept(Visitor visitor);
}
