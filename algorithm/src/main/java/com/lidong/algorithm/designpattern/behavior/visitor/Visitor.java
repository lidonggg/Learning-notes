package com.lidong.algorithm.designpattern.behavior.visitor;

/**
 * 提供统一的访问入口
 * 之所以要这么做，是因为面向对象语言有一大限制：
 * 我们知道，多态是一种动态绑定，可以在运行时获取对象的实际类型，来运行实际类型对应的方法。
 * 而函数重载是一种静态绑定，在编译时并不能获取对象的实际类型，而是根据声明类型执行声明类型对应的方法。
 * <p>
 * 否则我们其实可以在每个操作类中都提供一系列针对不同类型的操作的重载函数，可以大大的简化代码，但由于受到上述规则的限制，这种做法是无法实现的。
 *
 * @author ls J
 * @date 2020/4/8 10:57
 */
public interface Visitor {

    /**
     * objA's visitor
     *
     * @param objA objA
     */
    void visit(ObjA objA);

    /**
     * objB's visitor
     *
     * @param objB objB
     */
    void visit(ObjB objB);
}
