package com.lidong.algorithm.designpattern.behavior.memento;

/**
 * 输入的内容
 *
 * @author ls J
 * @date 2020/4/13 10:22
 */
public class InputText {

    private StringBuilder text = new StringBuilder();

    public String getText() {
        return text.toString();
    }

    public void append(String input) {
        text.append(input);
    }

    public Snapshot createSnapshot() {
        return new Snapshot(text.toString());
    }

    /**
     * 保存当前快照
     * 由于快照是全量备份，这里同样执行全量的替换
     * 如果是增量快照，这里可以根据快照长度来进行 replace
     *
     * @param snapshot 当前快照
     */
    public void restoreSnapshot(Snapshot snapshot) {
        this.text.replace(0, this.text.length(), snapshot.getText());
    }
}
