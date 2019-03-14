package com.concurrency.example.lock;

import com.concurrency.annotations.NotThreadSafe;
import com.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 没有任何读写锁的时候才可以执行写入的操作
 * 自己封装的map 不想把所有的函数暴露给别人 于是自己单独封装方法给外部用 又怕线程不安全 所以引入ReentrantReadWriteLock
 * 在读和写的时候分别加锁
 * 悲观读取
 * 如果获取写入锁  坚决不允许还有所有读取锁的存在
 * 如果读取很多的时候 写入一直在等待  导致写锁永远没有办法执行 饥饿
 */
@Slf4j
@ThreadSafe
public class LockExample2 {

    private final Map<String, Data> map = new TreeMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public static void main(String[] args) throws Exception{

    }

    public Data get(String key) {
        readLock.lock();
        try {
          return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value) {
        writeLock.lock(); // 写操作保证当前没有其他操作 如果总有读的操作所以写的锁会一直等待 就会造成饥饿状态
        try {
           return  map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    class Data {

    }
}
