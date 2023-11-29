package com.windcoder.threads.platform;

public class ComThread {
    public static void main(String[] args) throws InterruptedException {
        Thread s = new Thread(){
            @Override
            public void run() {
                System.out.println("sddd");
            }
        };
        s.start();

        Thread.sleep(100);

        System.out.println("结束了");
    }
}
