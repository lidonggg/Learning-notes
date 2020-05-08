package com.lidong.algorithm.designpattern.structure.chain.listmethod;

/**
 * 职责链模式 -- 链表实现
 *
 * @author ls J
 * @date 2020/3/25 13:33
 */
public class HandlerChain {

    /**
     * 记录链头，方便开始处理
     */
    private BaseHandler head = null;
    /**
     * 记录链尾，方便添加新的处理器
     */
    private BaseHandler tail = null;

    public void addHandler(BaseHandler handler) {
        handler.setSuccessor(null);
        if (head == null) {
            head = handler;
            tail = handler;
            return;
        }
        // 这两行代码顺序不能交换
        tail.setSuccessor(handler);
        tail = handler;
    }

    public void handle() {
        if (head != null) {
            head.handle();
        }
    }

    public static void main(String[] args) {
        HandlerChain handlerChain = new HandlerChain();
        handlerChain.addHandler(new HandlerA());
        handlerChain.addHandler(new HandlerB());
        handlerChain.handle();
    }
}
