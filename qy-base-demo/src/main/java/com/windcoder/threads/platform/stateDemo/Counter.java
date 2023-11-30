package com.windcoder.threads.platform.stateDemo;

/**
 * 计数器类
 */
public class Counter {
    int counter = 0;

    /**
     *  counter++ 操作
     *  synchronized 保证线程安全
     */
    public synchronized void increase() {
        counter++;
        // 通过 sleep 模拟耗时较长逻辑
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
