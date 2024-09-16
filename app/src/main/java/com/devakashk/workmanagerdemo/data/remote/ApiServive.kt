package com.devakashk.workmanagerdemo.data.remote

import com.devakashk.workmanagerdemo.data.models.QuotesDto
import retrofit2.http.GET

interface ApiServive {

    @GET("quotes/random")
    suspend fun getQuotesRandom(): QuotesDto
}