package com.windcoder.threads.virtuals;

import java.util.concurrent.Executors;

public class ExeVirtualThreadExample {
    public static void main (String[] args) throws InterruptedException {
        Runnable task = () -> {
            System.out.println("~~~~~~~~~~~~~~~ start ~~~~~~~~~~~~~~~ ");
            System.out.println("Executors Name: "+Thread.currentThread()+", isVirtual: "+ Thread.currentThread().isVirtual());
            System.out.println("~~~~~~~~~~~~~~~ end ~~~~~~~~~~~~~~~ ");
        };

        // Executors 工厂添加的对应工厂方法
        // 当有一个基于 ExecutorService 构建的并发程序，并且想检查虚拟线程可以带来怎样的收益时可用此方式。
        var service = Executors.newVirtualThreadPerTaskExecutor();
        // 每次提交时，获得的 ExecutorService 实际上会按需创建一个虚拟线程，并不汇集任何东西
        service.submit(task);


        //
        // virtualThread.join();

        Thread.sleep(200);
        System.out.println("Name: "+Thread.currentThread()+", isVirtual: "+ Thread.currentThread().isVirtual());
    }
}
