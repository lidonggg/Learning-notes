package com.lidong.algorithm.designpattern.behavior.visitor;

/**
 * @author ls J
 * @date 2020/4/8 10:58
 * 操作 B
 */
public class OperateB implements Visitor {
    @Override
    public void visit(ObjA objA) {
        System.out.println("ObjA's operateB");
    }

    @Override
    public void visit(ObjB objB) {
        System.out.println("ObjB's operateB");
    }
}
