package com.devakashk.workmanagerdemo.presentations.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devakashk.workmanagerdemo.domain.models.Quote
import com.devakashk.workmanagerdemo.domain.use_cases.GetAllQuotesFromDbUseCase
import com.devakashk.workmanagerdemo.domain.use_cases.GetQuoteUseCase
import com.devakashk.workmanagerdemo.domain.use_cases.SetupPeriodicWorkRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllQuotesFromDbUseCase: GetAllQuotesFromDbUseCase,
    private val getQuoteUseCase: GetQuoteUseCase,
    private val setupPeriodicWorkRequestUseCase: SetupPeriodicWorkRequestUseCase
) : ViewModel() {

    val uiState=getAllQuotesFromDbUseCase.invoke()
        .map { UiState(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, UiState(emptyList()))

    init {
        setupPeriodicWorkRequestUseCase.invoke()
    }

    fun getQuote() = getQuoteUseCase.invoke()


}

data class UiState(val data: List<Quote>)