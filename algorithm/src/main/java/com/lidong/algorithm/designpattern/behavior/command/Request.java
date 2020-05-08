package com.lidong.algorithm.designpattern.behavior.command;

/**
 * request data
 *
 * @author ls J
 * @date 2020/4/15 13:35
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
