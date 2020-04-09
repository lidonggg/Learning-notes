package com.lidong.algorithm.designpattern.behavior.visitor;

import java.util.Arrays;
import java.util.List;

/**
 * @author ls J
 * @date 2020/4/8 11:07
 * 访问者模式：允许一个或者多个操作应用到同一组对象上，解耦操作和对象本身
 */
public class Test {

    public static void main(String[] args) {
        OperateA operateA = new OperateA();
        OperateB operateB = new OperateB();
        List<BaseObj> objs = Arrays.asList(new ObjA(), new ObjB());
        for (BaseObj obj : objs) {
            obj.accept(operateA);
            obj.accept(operateB);
        }
    }
}
