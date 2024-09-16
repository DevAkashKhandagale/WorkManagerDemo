package com.devakashk.workmanagerdemo.data.repository


import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.devakashk.workmanagerdemo.data.local.QuoteDao
import com.devakashk.workmanagerdemo.data.models.QuotesDto
import com.devakashk.workmanagerdemo.data.worker.FetchWorker
import com.devakashk.workmanagerdemo.data.worker.PeriodicWorker
import com.devakashk.workmanagerdemo.domain.models.Quote
import com.devakashk.workmanagerdemo.domain.respository.QuotesRepository
import kotlinx.coroutines.flow.Flow

class QuotesRepositoryImpl(private val workManager: WorkManager, private val quoteDao: QuoteDao) :
    QuotesRepository {

    override fun getQuotes() {

        val constaints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED).build()

        val workRequest = OneTimeWorkRequestBuilder<FetchWorker>()
            .setConstraints(constaints)
            .build()
        workManager.enqueue(workRequest)

    }

    override fun getAllQuotes(): Flow<List<Quote>> = quoteDao.getAllQuotes()

    override fun setPeriodicRequest() {
        val constaints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED).build()

        val workRequest = PeriodicWorkRequest.Builder(
            PeriodicWorker::class.java,
            15,
            java.util.concurrent.TimeUnit.MINUTES
        )
            .setConstraints(constaints).build()

        workManager.enqueueUniquePeriodicWork(
            "someuqniuework",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }
}