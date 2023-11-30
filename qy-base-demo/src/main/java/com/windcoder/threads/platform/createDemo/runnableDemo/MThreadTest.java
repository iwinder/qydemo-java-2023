package com.windcoder.threads.platform.createDemo.runnableDemo;

public class MThreadTest {
    public static void main(String[] args) {
        //  MThread 实例化
        MThread r = new MThread();
        Thread t = new Thread(r);
        t.start();

        // 基于匿名内部类
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("这是一个实现 Runnable 的匿名内部类，重写 run 方法");
            }
        });
        t2.start();

        // 使用 Lambda 表达式
        Thread t3 = new Thread(()->{
                System.out.println("这是一个使用 Lambda 表达式实现 Runnable 的匿名内部类，重写 run 方法");

        });
        t3.start();

        // 工厂方式
        Thread t4 = Thread.ofPlatform().unstarted(r);
        t4.setName("平台线程");
        t4.start();


    }
}
