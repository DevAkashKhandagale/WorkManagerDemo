package com.devakashk.workmanagerdemo.domain.use_cases

import com.devakashk.workmanagerdemo.domain.respository.QuotesRepository
import javax.inject.Inject


class GetAllQuotesFromDbUseCase @Inject constructor(private val quuoteRepository: QuotesRepository) {

    operator fun invoke()= quuoteRepository.getAllQuotes()
}