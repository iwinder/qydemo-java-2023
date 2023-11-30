package com.windcoder.threads.platform.stateDemo;

/**
 * 用于验证 await()/signal();
 */
public class BookStorageOfLockTest {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000); // 确保 VisualVM 监控到购书线程状态变更过程
        BookStorage bs = new BookStorage();
        Thread buy = new Thread(()->{
            bs.buyByLock(20);
        },"购书");
        buy.start();
        System.out.println("购书线程状态 start："+buy.getState());

        Thread.sleep(10000); // 确保购书线程已经得到执行

        Thread storage = new Thread(()->{
            bs.storageByLock(30);
        },"补充库存");
        storage.start();

        Thread.sleep(100); // 确保购书线程已经被通知到
        System.out.println("购书线程状态 end："+buy.getState());

    }
}
