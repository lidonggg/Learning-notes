package com.lidong.algorithm.designpattern.structure.chain.arraymethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 职责链模式 -- 数数组实现
 *
 * @author ls J
 * @date 2020/3/25 13:33
 */
public class HandlerChain {

    /**
     * 使用数组存储
     */
    private List<IHandler> handlers = new ArrayList<>();

    public void addHandler(IHandler handler) {
        this.handlers.add(handler);
    }

    public void handle() {
        for (IHandler handler : handlers) {
            boolean handled = handler.handle();
            if (handled) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        HandlerChain handlerChain = new HandlerChain();
        handlerChain.addHandler(new HandlerA());
        handlerChain.addHandler(new HandlerB());
        handlerChain.handle();
    }
}
