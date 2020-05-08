package com.lidong.algorithm.designpattern.structure.adaptor.objectbase;

import com.lidong.algorithm.designpattern.structure.adaptor.Adaptee;
import com.lidong.algorithm.designpattern.structure.adaptor.ITarget;

/**
 * 对象适配器：基于组合
 *
 * @author Ls J
 * @date 2020/4/5 3:55 PM
 */
public class Adaptor implements ITarget {

    private Adaptee adaptee;

    Adaptor(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void f1() {
        // rewrite
    }

    @Override
    public void f2() {

    }

    @Override
    public void f3() {

    }

    @Override
    public void f4() {

    }
}
