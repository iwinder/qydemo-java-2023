package com.windcoder.threads.platform.stateDemo;

/**
 *  Counter 测试类
 *  用于验证 synchronized 阻塞线程状态
 */
public class CounterOfBlockedTest {
    public static void main(String[] args) throws InterruptedException {
        // 确保能截取到状态
        // Thread.sleep(1000);
        Counter c = new Counter();
        // 线程 t1 创建并先执行
        Thread t1 = new Thread(()->{
            c.increase();
        },"t1 线程");
        t1.start();

        // 线程 t2 创建并执行
        Thread t2 = new Thread(()->{
            c.increase();
        },"t2 线程");
        t2.start();

        // 确保 t2 run已经得到执行
        Thread.sleep(1000);
        // 检测 t2 此时的状态
        System.out.println("t2 state:"+t2.getState());
    }

}
