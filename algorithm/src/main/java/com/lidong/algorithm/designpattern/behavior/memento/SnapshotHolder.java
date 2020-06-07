package com.lidong.algorithm.designpattern.behavior.memento;

import java.util.Stack;

/**
 * @author ls J
 * @date 2020/4/13 10:16
 */
public class SnapshotHolder {

    /**
     * 定义一个栈，用于执行添加和撤销操作
     * 这里的快照是全量备份，栈中的每个元素都代表一个完整的已输入内容，适合输入文本比较小的情况
     * 对于大文本操作，这里将会比较消耗内存，我们可以采用增量备份的方式来实现，每个快照只保存当前改动的内容
     * <p>
     * 如果只用这一个栈，那么撤销操作是不可逆的，我们也可以再额外定义一个存放被撤销的快照的栈，从而可以支持可逆的撤销操作
     */
    private final Stack<Snapshot> snapshots = new Stack<>();

    /**
     * 执行撤销操作
     *
     * @return snapshot popped
     */
    public Snapshot popSnapshot() {
        return snapshots.pop();
    }

    /**
     * 执行添加操作
     *
     * @param snapshot snapshot to add
     */
    public void pushSnapshot(Snapshot snapshot) {
        snapshots.push(snapshot);
    }
}
