package com.backpacker.yflLibrary.java.asyn;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author : YFL  is Creating a porject in My Application
 * @Package com.example.myapplication.asynctaskscheduler
 * @Email : yufeilong92@163.com
 * @Time :2020/11/10 16:16
 * @Purpose : 异步线程
 */
public abstract class SingleAsyncTask<Progress,Result>  {
    private final AtomicBoolean mIsCancelled = new AtomicBoolean();

    private final static Handler sUIHandler = new InternalHandler();

    private static final int SINGLE_TASK_EXECUTED_RESULT = 0x1;
    private static final int SINGLE_TASK_EXECUTED_PROGRESS = 0x2;
    private FutureTask<Result> mFutureResult;

    protected SingleAsyncTask() {

        Callable<Result> taskCallable = new Callable<Result>() {
            @Override
            public Result call() throws Exception {
                Binder.flushPendingCommands();
                return doInBackground();
            }
        };

        mFutureResult = new FutureTask<Result>(taskCallable) {
            @Override
            protected void done() {
                super.done();
                try {
                    postResult(get());
                } catch (Exception e) {
                    onExecuteFailed(e);
                }
            }
        };
    }

    public void executeSingle() {
        Thread workThread= new Thread(mFutureResult);
        workThread.start();
    }

    private void postResult(Result result) {
        if(mIsCancelled.get()) {
            return;
        }
        sUIHandler.obtainMessage(SINGLE_TASK_EXECUTED_RESULT,new AsyncTaskResult<Result>(this,result)).sendToTarget();
    }


    FutureTask<Result> getFutureTask() {
        return mFutureResult;
    }

    @WorkerThread
    protected abstract Result doInBackground();

    @MainThread
    protected void onProgressUpdate(Progress values) {
    }

    @MainThread
    protected void onExecuteSucceed(Result result){

    }

    @WorkerThread
    protected void onExecuteFailed(Exception exception){

    }

    @MainThread
    protected void onExecuteCancelled(Result result){

    }

    @WorkerThread
    protected final void publishProgress(Progress  values) {
        if (!isCancelled()) {
            sUIHandler.obtainMessage(SINGLE_TASK_EXECUTED_PROGRESS,
                    new AsyncTaskResult<Progress>(this, values)).sendToTarget();
        }
    }

    private void finish(Result result) {

        if (isCancelled()) {
            onExecuteCancelled(result);
        } else {
            onExecuteSucceed(result);
        }
    }

    public final boolean cancel(boolean mayInterruptIfRunning) {
        mIsCancelled.set(true);
        return mFutureResult.cancel(mayInterruptIfRunning);
    }


    public boolean isCancelled() {
        return mIsCancelled.get();
    }


    private static class AsyncTaskResult<Data> {
        final SingleAsyncTask mSingleAsyncTask;
        final Data mData;


        AsyncTaskResult(SingleAsyncTask singleAsyncTask, Data  data) {
            mSingleAsyncTask = singleAsyncTask;
            mData = data;
        }
    }

    private static class InternalHandler extends Handler {
        InternalHandler() {
            super(Looper.getMainLooper());
        }
        @SuppressWarnings({"unchecked", "RawUseOfParameterizedType"})
        @Override
        public void handleMessage(Message msg) {
            AsyncTaskResult<?> asyncTaskResult = (AsyncTaskResult<?>) msg.obj;
            switch (msg.what) {
                case SINGLE_TASK_EXECUTED_RESULT:
                    asyncTaskResult.mSingleAsyncTask.finish(asyncTaskResult.mData);
                    break;
                case SINGLE_TASK_EXECUTED_PROGRESS:
                    asyncTaskResult.mSingleAsyncTask.onProgressUpdate(asyncTaskResult.mData);
                    break;
            }
        }
    }
}
