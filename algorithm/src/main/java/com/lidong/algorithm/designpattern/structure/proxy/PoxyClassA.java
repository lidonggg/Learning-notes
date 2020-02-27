package com.lidong.algorithm.designpattern.structure.proxy;

/**
 * @author ls J
 * @date 2020/2/27 19:15
 */
public class PoxyClassA implements IClass {

    private IClass c;

    public PoxyClassA(IClass c) {
        this.c = c;
    }

    @Override
    public void fun() {
        // 新增代码
        c.fun();
        // 新增代码
    }
}
