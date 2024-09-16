package com.devakashk.workmanagerdemo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devakashk.workmanagerdemo.domain.models.Quote

@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class QuotesDatabase : RoomDatabase() {
    companion object {
        fun getInstance(context: Context) = Room.databaseBuilder(
            context,
            QuotesDatabase::class.java,
            "quotes.db"
        ).build()
    }

    abstract fun getQuoteDao(): QuoteDao
}