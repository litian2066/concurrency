package com.concurrency.example.commonUnSafe;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class ArrayListExample {

    // 请求总数
    private final static  int  threadTotal = 5000;

    // 同时执行请求数
    private final  static  int clentTotal = 200;

    private static List<Integer> arrayList = new ArrayList<Integer>();

    public static void main(String[] args) throws  Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();

        CountDownLatch countDownLatch = new CountDownLatch(threadTotal);

        Semaphore semaphore = new Semaphore(clentTotal);

        for (int i = 0; i < threadTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("Exception: ", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(); // 保证线程都执行完'
        log.info("{}", arrayList.size());
        executorService.shutdown();

    }

    private static void update(int count) {
        arrayList.add(count);
    }

}
