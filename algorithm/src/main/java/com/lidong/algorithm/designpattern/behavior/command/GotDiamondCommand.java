package com.lidong.algorithm.designpattern.behavior.command;

/**
 * @author ls J
 * @date 2020/4/15 13:31
 */
public class GotDiamondCommand implements Command {
    @Override
    public void execute() {
        System.out.println("my command 1 executed");
    }
}
