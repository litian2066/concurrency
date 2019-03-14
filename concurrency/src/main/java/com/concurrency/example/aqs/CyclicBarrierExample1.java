package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CyclicBarrierExample1 {

    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i < 11; i++) {
            Thread.sleep(1000);
            final int count = i;
            executorService.execute(() -> {
                try {
                    race(count);
                } catch (Exception e) {
                    log.error("exception: ", e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(final int n) throws Exception{
        Thread.sleep(1000);
        System.out.println(n + " is ready: ");
        cyclicBarrier.await();
        System.out.println(n + " is continue");
        Thread.sleep(1000);
    }
}

