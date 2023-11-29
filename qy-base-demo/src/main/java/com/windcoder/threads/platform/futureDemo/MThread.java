package com.windcoder.threads.platform.futureDemo;

import java.util.concurrent.Callable;

public class MThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "这是一个实现 Callable 的方式，执行 call 方法"+Thread.currentThread()+"-"+Thread.currentThread().isDaemon();
    }
}
