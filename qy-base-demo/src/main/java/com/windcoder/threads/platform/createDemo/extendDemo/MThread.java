package com.windcoder.threads.platform.createDemo.extendDemo;

public class MThread extends Thread{
    @Override
    public void run() {
        System.out.println("这是一个继承 Thread，重写 run 方法");
    }
}
