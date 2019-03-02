package com.concurrency.example.thredLocal;

public class RequestHolder {

    // Long 单纯打印线程的ID
    private final static ThreadLocal<Long> reqeuestHolder = new ThreadLocal<>();

    // 提供传入数据的方法
    // 默认会取出当前线程的ID放到Map里面去 key 是当前线程的变量 而value值是我们传入的Long型的变量
    // 请求进入到后端没有进行实际处理的时候执行
    public static void add(Long id) {
        reqeuestHolder.set(id);
    }

    public static Long getId() {
        return reqeuestHolder.get();
    }

    // 不移除会造成内存溢出 因为他会伴随这个整个项目运行
    // 需要接口真正处理的时候调用 使用interceptor
    public static void remove() {
        reqeuestHolder.remove();
    }
}
