package com.devakashk.workmanagerdemo.data.di

import android.app.Application
import android.content.Context
import androidx.work.WorkManager
import com.devakashk.workmanagerdemo.data.local.QuoteDao
import com.devakashk.workmanagerdemo.data.local.QuotesDatabase
import com.devakashk.workmanagerdemo.data.remote.ApiServive
import com.devakashk.workmanagerdemo.data.repository.QuotesRepositoryImpl
import com.devakashk.workmanagerdemo.domain.respository.QuotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiServive {
        return retrofit.create(ApiServive::class.java)
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): QuotesDatabase {
        return QuotesDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun providesDao(quotesDatabase: QuotesDatabase): QuoteDao {
        return quotesDatabase.getQuoteDao()
    }

    @Provides
    @Singleton
    fun providesWorkManager(context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Provides
    fun provideQuoteRepository(workManager: WorkManager, quoteDao: QuoteDao): QuotesRepository =
        QuotesRepositoryImpl(workManager, quoteDao)
}