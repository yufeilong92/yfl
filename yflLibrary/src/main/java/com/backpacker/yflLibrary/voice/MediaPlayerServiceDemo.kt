package com.backpacker.yflLibrary.voice

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * @Author : YFL  is Creating a porject in workerApp_Android
 * @Package cn.ruiye.worker.service
 * @Email : yufeilong92@163.com
 * @Time :2020/11/28 11:42
 * @Purpose :
 */
private const val EXTRA_PARAM1 = "cn.ruiye.worker.service.extra1.PARAM1"
private const val EXTRA_PARAM2 = "cn.ruiye.worker.service.extra1.PARAM2"

class MediaPlayerService : Service() {

    private var mParam1: String? = null
    private var mParam2: String? = null

    companion object {

        @JvmStatic
        fun startAction(context: Context, param1: String, param2: String) {
            val intent = Intent(context, MediaPlayerService::class.java).apply {
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }

    }


    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            mParam1 = it.getStringExtra(EXTRA_PARAM1)
            mParam2 = it.getStringExtra(EXTRA_PARAM2)
        }
        initData()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun initData() {
        var task: VoiceTask? = null
        var number = 0
        when (mParam1) {
            "1" -> {
                number = 0
                for (index in 0..3) {
                    task = VoiceTask()
                    task.setId(0)
                    task.assesName = "rcc.mp3"
                    task.setListener(object : VoiceListener() {
                        override fun onCompleted(task: VoiceTask) {
                            ++number
                            if (number == 3) {
                                VoiceManager.getInstance().cancelCurrentTask(task)
                                stopSelf()
                            }
                            Log.e("===", "播放完成：$task")
                        }

                        override fun onError(task: VoiceTask?, error: VoiceError) {
                            super.onError(task, error)

                            Log.e("===", error.toString())
                        }

                        override fun onStart(task: VoiceTask) {
                            super.onStart(task)
                            Log.e("===", task.toString())
                        }
                    })
                    VoiceManager.getInstance().submit(this, task)
                }
            }
            "2" -> {
                number = 0
                for (index in 0..3) {
                    task = VoiceTask()
                    task.setId(0)
                    task.assesName = "overtime.mp3"
                    task.setListener(object : VoiceListener() {
                        override fun onCompleted(task: VoiceTask) {
                            ++number
                            if (number == 3) {
                                VoiceManager.getInstance().cancelCurrentTask(task)
                                stopSelf()
                            }
                            Log.e("===", "播放完成：$task")
                        }

                        override fun onError(task: VoiceTask?, error: VoiceError) {
                            super.onError(task, error)

                            Log.e("===", error.toString())
                        }

                        override fun onStart(task: VoiceTask) {
                            super.onStart(task)
                            Log.e("===", task.toString())
                        }
                    })
                    VoiceManager.getInstance().submit(this, task)
                }
            }
            else -> {
                stopSelf()
            }

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        VoiceManager.getInstance().clearAndRelease()
    }

}