package com.lidong.algorithm.designpattern.behavior.command;

/**
 * @author ls J
 * @date 2020/4/15 13:32
 */
public class GotStarCommand implements Command {
    @Override
    public void execute() {
        System.out.println("my command 2 executed");
    }
}
