package com.concurrency.example.atomicExample;

import com.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class ConcurrencyExample1 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static int count;

    public static void main(String[] args) throws Exception{
        // 初始化线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量 十字路口同时让多少汽车通过
        final Semaphore semaphore = new Semaphore(threadTotal); // 允许并发数
        // 定义计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal); // 希望执行完后统计总数 这儿传入请求总数
        for (int i = 0; i < clientTotal; i++) {
            // 放入请求
            executorService.execute(() -> {
                try {
                    semaphore.acquire(); // 当前线程是否允许被执行
                    add();
                    semaphore.release(); // 释放线程
                } catch (Exception e) {
                    log.error("Exception", e);
                }
                countDownLatch.countDown(); // 计数
            });
        }
        countDownLatch.await(); // 保证countDown减为0
        executorService.shutdown();
        log.info("count: {}", count);
    }

    private static void add() {
        count++;
    }
}
