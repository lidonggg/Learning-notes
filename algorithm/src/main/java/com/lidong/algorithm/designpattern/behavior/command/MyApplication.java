package com.lidong.algorithm.designpattern.behavior.command;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ls J
 * @date 2020/4/15 13:33
 * 轮询命令，并执行相应的操作
 */
public class MyApplication {

    private static final int MAX_HANDLED_REQ_COUNT_PRE_LOOP = 100;

    private Queue<Command> queue = new LinkedList<>();

    public void mainLoop() {
        while (true) {
            List<Request> requests = new ArrayList<>();
            // TODO get requests
            // 模拟数据
            requests.add(new Request(Event.GOT_DIAMOND));
            requests.add(new Request(Event.GOT_STAR));

            for (Request request : requests) {
                Event event = request.getEvent();
                Command command = null;
                if (Event.GOT_DIAMOND.equals(event)) {
                    command = new GotStarCommand();
                } else if (Event.GOT_STAR.equals(event)) {
                    command = new GotStarCommand();
                }
                if (null != command) {
                    queue.add(command);
                }
            }
            int handledCount = 0;
            while (handledCount < MAX_HANDLED_REQ_COUNT_PRE_LOOP) {
                if (queue.isEmpty()) {
                    break;
                }
                Command command = queue.poll();
                command.execute();
                handledCount++;
            }
        }
    }

    public static void main(String[] args) {
        MyApplication application = new MyApplication();
        application.mainLoop();
    }
}
