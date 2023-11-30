package com.windcoder.threads.platform.stateDemo;

/**
 *  用于验证 waity()/notify()
 */
public class BookStorageOfReenterBlockedTest {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000); // 确保 VisualVM 监控到购书线程状态变更过程
        BookStorage bs = new BookStorage();
        Thread buy = new Thread(()->{
            bs.buy(20);
            // if 会导致虚假唤醒
//            bs.buyByIf(20);
        },"购书");
        buy.start();
        System.out.println("购书线程状态 start："+buy.getState());

        Thread.sleep(10000); // 确保购书线程已经得到执行

        Thread storage = new Thread(()->{
            // 如果最终库存总数少于20,调用 buyByIf 会进入虚假唤醒，导致最终库存为负
            bs.storage(20);
        },"补充库存");
        storage.start();

        Thread.sleep(100); // 确保购书线程已经被通知到
        System.out.println("购书线程状态 end："+buy.getState());

    }
}
