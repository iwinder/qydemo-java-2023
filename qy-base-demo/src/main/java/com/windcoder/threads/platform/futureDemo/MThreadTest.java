package com.windcoder.threads.platform.futureDemo;

import java.util.concurrent.*;

public class MThreadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> f = new FutureTask<String>(new MThread());
        Thread t = new Thread(f);
        // 查看线程状态
        System.out.println("main 获取 t 的状态-start 前："+t.getState());
        t.start();

        System.out.println("main 获取 f 的结果："+f.get());
        // 查看线程状态
        System.out.println("main 获取 t 的状态-start 后："+t.getState());
        // 测试多次执行 start
        // t.start();

        
        // Lambda
        FutureTask<String> f2 = new FutureTask<String>(()->{
           return "这是一个使用 Lambda 表达式实现 Callable 的的匿名内部类";
        });
        Thread t2 = new Thread(f2);
        t2.start();
        System.out.println("main 获取 f2 的结果："+f2.get());

        // 线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new MThread());
        System.out.println("这是一个基于线程池运行的 Future："+future.get());
        executor.close();

        try {
            test2();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //采用了competableFuture，采用回调的方式获取任务的返回值
    public static void test2() throws Exception {
        //supplyAsync 内部默认使用ForkJoinPool线程池执行任务，可通过类似 supplyAsync(Supplier<U> supplier,Executor executor)  指定自己的线程池
        CompletableFuture<String> completableFuture=CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            System.out.println("这是一个基于 CompletableFuture 和默认线程池的实例：" + Thread.currentThread().isDaemon());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "100";
        }).whenComplete((v,r)->{
            System.out.println("计算结果是: "+v);
        });
        //默认使用的线程池里的线程是 daemon 的。main线程结束后，整个程序也结束了
        // 这里通过 join() 用于等待 completableFuture 线程终止
        completableFuture.join();
        // 类似也可以将main线程join 保证任务里的代码执行完-仅限测试
        // Thread.currentThread().join(10000);
    }
}
