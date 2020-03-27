package com.lidong.algorithm.designpattern.structure.chain.listmethod;

/**
 * @author ls J
 * @date 2020/3/25 13:32
 */
public class HandlerB extends BaseHandler {

    @Override
    protected boolean doHandle() {
        boolean handled = false;
        // TODO 业务代码
        System.out.println("HandleB invoke failed");
        return handled;
    }
}
