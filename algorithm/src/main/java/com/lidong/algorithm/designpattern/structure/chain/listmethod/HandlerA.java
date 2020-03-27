package com.lidong.algorithm.designpattern.structure.chain.listmethod;

/**
 * @author ls J
 * @date 2020/3/25 13:32
 */
public class HandlerA extends BaseHandler {

    @Override
    protected boolean doHandle() {
        boolean handled = true;
        // TODO 业务代码
        System.out.println("HandleA invoke succeeded");
        return handled;
    }
}
