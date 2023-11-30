package com.windcoder.threads.platform.createDemo.extendDemo;

public class MThreadTest {
    public static void main(String[] args) {

        // MThread 实例化
        MThread t = new MThread();
        t.start();
        System.out.println("t1 结束了");

        // 匿名内部类
        Thread t2 = new Thread(){
            @Override
            public void run() {
                System.out.println("这是一个继承 Thread 的匿名内部类，重写 run 方法");
            }
        };
        t2.start();
        System.out.println("t2 结束了");
    }
}
