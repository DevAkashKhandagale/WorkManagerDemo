package com.devakashk.workmanagerdemo.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.devakashk.workmanagerdemo.data.local.QuoteDao
import com.devakashk.workmanagerdemo.data.mappers.toDomain
import com.devakashk.workmanagerdemo.data.remote.ApiServive
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


const val ONE_TIME_WORK_REQUEST="ONE_TIME_WORK_REQUEST"

@HiltWorker
class FetchWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParams: WorkerParameters,
    private val apiServive: ApiServive,
    private val quoteDao: QuoteDao
) : CoroutineWorker(context, workerParams) {


    override suspend fun doWork(): Result {
          return try{
              val response=apiServive.getQuotesRandom().toDomain(ONE_TIME_WORK_REQUEST)
              quoteDao.insert(response)
              Result.success()
          }catch (e:Exception){
              Result.failure()
          }
    }
}