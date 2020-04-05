package com.lidong.algorithm.designpattern.structure.adaptor.classbase;

import com.lidong.algorithm.designpattern.structure.adaptor.Adaptee;
import com.lidong.algorithm.designpattern.structure.adaptor.ITarget;

/**
 * @author Ls J
 * @date 2020/4/5 3:54 PM
 * 类适配器：基于继承
 */
public class Adaptor extends Adaptee implements ITarget {
    @Override
    public void f1() {
        super.fa();
    }

    @Override
    public void f2() {
        // 重新实现 f2
    }

    @Override
    public void f3() {
        // 。。。。
    }

    @Override
    public void f4() {
        // 。 。。。
    }
}
