package com.windcoder.threads.platform.stateDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 书籍库存 对象
 */
public class BookStorage {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    // 总数
    int num = 3;

    /**
     * 增加库存 根据 notify() 所在位置不同，可获得 wait()线程的不同状态
     * @param newNum
     */
    public  synchronized void storage(int newNum) {
        // 增加库存
        num += newNum;
        // 通知 -- 检测   Object.wait with no timeout
//        notify();
        try {
            Thread.sleep(10000); // 通知后却暂时不退出
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 通知 -- 检测  Object.wait with timeout
        notify();
    }

    /**
     * 购买后减库存 用于验证 wait(); 与 wait(1000);
     * @param needNum
     */
    public synchronized void buy(int needNum) {
        while (needNum > num) {
            try {
                wait();
//                wait(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // 实际待执行的 扣除库存的逻辑
        num -= needNum;
        // 为了监测状态变化
        int i=0;
        while (i<10000000) {
            i++;
            System.out.println("购书线程状态 end："+num +", cout :"+i);
        }
    }

    /**
     * 购买后减库存 用于验证 wait 在 if 中时出现的虚假唤醒
     * @param needNum
     */
    public synchronized void buyByIf(int needNum) {
        if (needNum > num) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // 实际待执行的 扣除库存的逻辑
        num -= needNum;
        // 为了监测状态变化
        int i=0;
        while (i<10000000) {
            i++;
            System.out.println("购书线程状态 end："+num +", cout :"+i);
        }
    }

    /**
     *  增加库存 signal() 用于验证 await();
     * @param newNum
     */
    public void storageByLock(int newNum) {
        lock.lock();	//锁定
        // 增加库存
        num += newNum;

        try {
            // 通知
            condition.signal();
            Thread.sleep(10000); // 通知后却暂时不退出
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }  finally {
            lock.unlock();
        }
    }

    /**
     * 购买后减库存 用于验证 await();
     * @param needNum
     */
    public void buyByLock(int needNum) {
        while (needNum > num) {
            lock.lock();	//锁定
            try {
                condition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
        // 实际待执行的 扣除库存的逻辑
        num -= needNum;
        // 为了监测状态变化
        int i=0;
        while (i<10000000) {
            i++;
            System.out.println("购书线程状态 end："+num +", cout :"+i);
        }
    }
}
