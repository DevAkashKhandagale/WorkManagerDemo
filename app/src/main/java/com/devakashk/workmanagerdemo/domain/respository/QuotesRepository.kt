package com.devakashk.workmanagerdemo.domain.respository

import com.devakashk.workmanagerdemo.domain.models.Quote
import kotlinx.coroutines.flow.Flow

interface QuotesRepository {

    fun getQuotes()

    fun getAllQuotes():Flow<List<Quote>>

    fun setPeriodicRequest()
}