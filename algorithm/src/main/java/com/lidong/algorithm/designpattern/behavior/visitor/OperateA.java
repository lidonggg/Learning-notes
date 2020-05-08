package com.lidong.algorithm.designpattern.behavior.visitor;

/**
 * 操作 A
 *
 * @author ls J
 * @date 2020/4/8 10:58
 */
public class OperateA implements Visitor {
    @Override
    public void visit(ObjA objA) {
        System.out.println("ObjA's operateA");
    }

    @Override
    public void visit(ObjB objB) {
        System.out.println("ObjB's operateA");
    }
}
