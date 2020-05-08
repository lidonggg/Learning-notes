package com.lidong.algorithm.designpattern.behavior.memento;

/**
 * 快照，本身不可变，只提供 get 方法
 *
 * @author ls J
 * @date 2020/4/13 10:14
 */
public class Snapshot {

    /**
     * 这里的快照是全量备份，每次执行都保存当前已输入的全部元素
     */
    private String text;

    public Snapshot(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
