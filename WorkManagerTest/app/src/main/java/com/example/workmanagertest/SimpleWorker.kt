package com.example.workmanagertest

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

private const val TAG = "SimpleWorker"

class SimpleWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.d(TAG, "do work in SimpleWorker")
        return Result.success()

    }

}

