package com.lidong.algorithm.designpattern.structure.decorator;

import com.lidong.algorithm.designpattern.structure.proxy.IClass;

/**
 * @author ls J
 * @date 2020/2/27 19:15
 */
public class DecoratorClassA implements IClass {

    private IClass c;

    public DecoratorClassA(IClass c) {
        this.c = c;
    }

    @Override
    public void fun() {
        // 功能增强代码
        c.fun();
        // 功能增强代码
    }
}
