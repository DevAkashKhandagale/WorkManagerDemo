package com.devakashk.workmanagerdemo.domain.use_cases

import com.devakashk.workmanagerdemo.domain.respository.QuotesRepository
import javax.inject.Inject

class GetQuoteUseCase @Inject constructor(private val quotesRepository: QuotesRepository) {

    operator fun invoke() = quotesRepository.getQuotes()
}