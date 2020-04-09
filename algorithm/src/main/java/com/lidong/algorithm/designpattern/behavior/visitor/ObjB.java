package com.lidong.algorithm.designpattern.behavior.visitor;

/**
 * @author ls J
 * @date 2020/4/8 11:00
 */
public class ObjB extends BaseObj {

    public ObjB() {
        super();
    }

    @Override
    public void accept(Visitor visitor) {
        // this 指向 ObjA 类型，这个在编译期间便已经确定了
        visitor.visit(this);
    }
}
