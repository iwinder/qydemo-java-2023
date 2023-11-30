package com.windcoder.threads.platform.stateDemo;

/**
 *  join 测试类
 */
public class JoinMainTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            System.out.println("t1 开始");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t1 结束");
        },"t1 线程");
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Thread t2 = new Thread(()->{
            System.out.println("t2 开始");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t2 结束");
        },"t2 线程");

        t2.start();

        t2.yield();
    }
}
