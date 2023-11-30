package com.windcoder.threads.platform.stateDemo;

import java.util.Scanner;

/**
 * 阻塞IO 时线程状态
 */
public class BlockedIOTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 命令行中的阻塞读
                    String input = in.nextLine();
                    System.out.println(input);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (in!=null) {
                        in.close();
                    }
                }
            }
        }, "阻塞I/O测试"); // 线程的名字

        // 启动
        t.start();


        try {
            Thread.sleep(100);  // 确保run已经得到执行
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("阻塞I/O测试的线程状态："+t.getState());
    }

}
