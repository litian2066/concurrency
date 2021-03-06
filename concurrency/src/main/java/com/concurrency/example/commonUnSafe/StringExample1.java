package com.concurrency.example.commonUnSafe;

import com.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class StringExample1 {

    // 请求总数
    private static Integer threadTotal = 5000;

    // 同时执行请求总数
    private static Integer clientTotal = 200;

    // StringBuild
    private static StringBuilder stringBuilder = new StringBuilder();


    public static void main(String[] args) throws  Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义信号量 允许同时执行请求200个
        final Semaphore semaphore = new Semaphore(clientTotal); // 允许并发数
        // 定义闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(threadTotal);

        for (int i = 0; i < threadTotal; i++) {
//            final int count = i;
            executorService.execute(() -> {
                try {
                    // 允许线程访问
                    semaphore.acquire(); // 当前线程是否允许被执行
                    doSomeThing();
                    semaphore.release(); // // 释放线程
                } catch (Exception e) {
                    log.error("error: ", e);
                }
                countDownLatch.countDown();
            });

        }

        countDownLatch.await();
        executorService.shutdown();
        log.info("result: {}", stringBuilder.length());
    }

    private static void doSomeThing() {
        stringBuilder.append("1");
    }


}
