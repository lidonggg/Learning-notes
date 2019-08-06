package com.lidong.java.concurrent.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author ls J
 * @date 2019/8/2 10:44 AM
 * <T> Future<T> submit(Runnable task, T result) 方法经典用法
 */
public class SubmitWithResultDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(1);

        Result result = new Result();
        Future<Result> future = es.submit(new Task(result),result);
        Result r = future.get();

        System.out.println(r.getCode());
    }
}

class Task implements Runnable {

    /**
     * result
     */
    private Result result;

    Task(Result result){
        this.result = result;
    }

    @Override
    public void run() {
        System.out.println("我被执行了");
        result.setCode(0);
        result.setMsg("success");
    }
}

class Result {

    /**
     * code
     */
    private int code;

    /**
     * message
     */
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
