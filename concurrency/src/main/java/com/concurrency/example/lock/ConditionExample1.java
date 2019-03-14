package com.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示Condition
 * 唤醒等待线程 重新争夺锁
 */
@Slf4j
public class ConditionExample1 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
           try {
               reentrantLock.lock();
               log.info("wait signal"); // 1. 第一步 该线程加入AQS等待队列
               condition.await();       // 该线程从AQS的等待队列里面移除 锁的释放 加入Condition的等待队列 线程一等待信号
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           log.info("get signal");   // 4. 第四步
           reentrantLock.unlock();  // 4.1 释放锁
        }).start();

        new Thread(() -> {
            reentrantLock.lock(); // 线程二因为线程一加入Condition的关系 所以这儿能获取锁 被唤醒 加入AQS等待队列
            log.info("get lock"); // 2. 第二步 所以输出...
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll(); // 发送新号 Condition的等待队列里面有线程一的一个节点 于是就被取出来加入了AQS的等待队列 但是没有
                                    // 被唤醒
            log.info("send signal");   // 3. 第三步
            reentrantLock.unlock();  // AQS释放锁 所以唤醒线程一 所以线程一继续执行  跳到第四部
        }).start();


    }
}
