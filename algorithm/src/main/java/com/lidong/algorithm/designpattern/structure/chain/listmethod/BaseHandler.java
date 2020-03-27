package com.lidong.algorithm.designpattern.structure.chain.listmethod;

/**
 * @author ls J
 * @date 2020/3/25 13:32
 */
public abstract class BaseHandler {

    protected BaseHandler successor = null;

    public void setSuccessor(BaseHandler successor) {
        this.successor = successor;
    }

    public final void handle() {
        boolean handled = doHandle();
        if (successor != null && !handled) {
            successor.doHandle();
        }
    }

    /**
     * do handle
     *
     * @return true if success
     */
    protected abstract boolean doHandle();
}
