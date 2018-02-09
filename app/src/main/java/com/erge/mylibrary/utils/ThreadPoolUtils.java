package com.erge.mylibrary.utils;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/6/1.
 * <p>
 * 线程池单例操作工具类
 */
public class ThreadPoolUtils {

    private static ThreadPoolUtils instance;

    private static ThreadPoolExecutor executor;
    // 核心线程数
    private static final int coreThreadNum = 5;
    // 最大线程数
    private static final int maxThreadNum = Integer.MAX_VALUE;
    // 非核心线程闲置超时时长
    private static final long keepAliveTime = 30 * 1000L;
    // keepAliveTime的单位
    private static final TimeUnit unit = TimeUnit.SECONDS;
    // 任务队列
    private static final SynchronousQueue<Runnable> workQueue = new SynchronousQueue<>();

    public static ThreadPoolUtils getInstance() {
        if (null == instance) {
            instance = new ThreadPoolUtils();
        }
        return instance;
    }

    private ThreadPoolUtils() {
        executor = new ThreadPoolExecutor(coreThreadNum, maxThreadNum, keepAliveTime,
                unit, workQueue);
        // 核心线程如果闲置一段时间，也会被销毁
        executor.allowCoreThreadTimeOut(true);
    }

    /**
     * 增加一个任务
     *
     * @param runnable
     */
    public void addTask(Runnable runnable) {
        executor.execute(runnable);
    }
}
