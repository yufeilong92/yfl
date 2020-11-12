package com.backpacker.yflLibrary.java.asyn;

import androidx.annotation.NonNull;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.example.myapplication.asynctaskscheduler
 * @Email : yufeilong92@163.com
 * @Time :2020/11/10 16:16
 * @Purpose : 异步线程
 */
public class AsyncTaskScheduler {
    private final CopyOnWriteArrayList<SingleAsyncTask> mSingleAsyncTaskList = new CopyOnWriteArrayList<SingleAsyncTask>();

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {

            return new Thread(r, "AsyncTaskScheduler  Thread#" + mCount.getAndIncrement());
        }
    };

    public static final Executor CACHED_THREAD_POOL = Executors.newCachedThreadPool(sThreadFactory);

    private  Executor mDefaultPoolExecutor  ;


    /**
     * set an {@link Executor} that can be used to execute tasks in parallel as default .
     */
    public AsyncTaskScheduler() {
        this.mDefaultPoolExecutor = CACHED_THREAD_POOL;
    }

    /**
     * you set an {@link Executor} that you what as default .
     */
    public AsyncTaskScheduler(Executor defaultPoolExecutor ) {
        this.mDefaultPoolExecutor = defaultPoolExecutor;
    }

    public AsyncTaskScheduler execute(@NonNull SingleAsyncTask singleAsyncTask){
        mDefaultPoolExecutor.execute(singleAsyncTask.getFutureTask());
        mSingleAsyncTaskList.add(singleAsyncTask);
        return this;
    }


    /**
     cancel a singleAsyncTask
     */
    public boolean cancelTask(SingleAsyncTask singleAsyncTask, boolean mayInterruptIfRunning) {
        return singleAsyncTask.cancel(mayInterruptIfRunning);
    }

    /**
     cancel all singleTask in the scheduler
     */
    public void cancelAllTasks(boolean mayInterruptIfRunning){
        for(SingleAsyncTask singleAsyncTask : mSingleAsyncTaskList) {
            cancelTask(singleAsyncTask,mayInterruptIfRunning);
        }
        mSingleAsyncTaskList.clear();
    }

//    public void shutDown() {
//        mDefaultPoolExecutor.shutdownNow();
//        synchronized (this) {
//            cancelAllTasks(true);
//        }
//    }
//
//    public boolean isShutDown() {
//        return mDefaultPoolExecutor.isShutdown();
//    }
}
