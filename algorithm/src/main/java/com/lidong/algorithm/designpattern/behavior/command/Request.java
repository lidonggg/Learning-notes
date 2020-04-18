package com.lidong.algorithm.designpattern.behavior.command;

/**
 * @author ls J
 * @date 2020/4/15 13:35
 * request data
 */
public class Request {

    private Event event;

    public Request(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
