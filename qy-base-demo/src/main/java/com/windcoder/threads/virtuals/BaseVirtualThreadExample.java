package com.windcoder.threads.virtuals;

import java.util.concurrent.ConcurrentHashMap;

public class BaseVirtualThreadExample {


    public static void main (String[] args) throws InterruptedException {
        Runnable task = () -> {
            System.out.println("~~~~~~~~~~~~~~~ start ~~~~~~~~~~~~~~~ ");
            System.out.println("Name: "+Thread.currentThread()+", isVirtual: "+ Thread.currentThread().isVirtual());
            System.out.println("~~~~~~~~~~~~~~~ end ~~~~~~~~~~~~~~~ ");
        };
        // 工厂方式 Thread.ofPlatform() 创建平台线程  Thread.ofVirtual() 创建虚拟线程
        Thread virtualThread = Thread.ofVirtual().unstarted(task);
//        virtualThread.setDaemon(false);
        virtualThread.isDaemon();
        System.out.println("virtualThread.isDaemon: "+virtualThread.isDaemon());
        virtualThread.start();
        //
        // virtualThread.join();

        Thread.sleep(200);
        System.out.println("Name: "+Thread.currentThread()+", isVirtual: "+ Thread.currentThread().isVirtual());

        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
    }

}


