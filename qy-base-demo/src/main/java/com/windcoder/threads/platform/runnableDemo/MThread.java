package com.windcoder.threads.platform.runnableDemo;

public class MThread implements Runnable{
    @Override
    public void run() {
        System.out.println("这是一个继承 Runnable ，重写 run 方法："+Thread.currentThread());
    }
}
