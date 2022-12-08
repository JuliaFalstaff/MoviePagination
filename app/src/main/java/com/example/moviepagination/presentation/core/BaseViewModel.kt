package com.example.moviepagination.presentation.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class BaseViewModel : ViewModel() {

    protected val viewModelCustomScope =
        CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
        )

    override fun onCleared() {
        super.onCleared()
        viewModelCustomScope.cancel()
    }
}