package com.example.moviepagination.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.GetTop250MoviesUseCase
import com.example.moviepagination.domain.usecases.GetTop250TvsUseCase
import com.example.moviepagination.presentation.core.BaseViewModel
import kotlinx.coroutines.launch

class Top250ViewModel(
    private val getTop250MoviesUseCase: GetTop250MoviesUseCase,
    private val getTop250TvsUseCase: GetTop250TvsUseCase
) : BaseViewModel() {

    private val _top250MoviesLiveData: MutableLiveData<AppState> = MutableLiveData()
    private val _top250TVShowLiveData: MutableLiveData<AppState> = MutableLiveData()
    val top250MoviesLiveData: LiveData<AppState> get() = _top250MoviesLiveData
    val top250TVShowLiveData: LiveData<AppState> get() = _top250TVShowLiveData

    init {
        loadTop250Movies()
        loadTop250TVs()
    }

    private fun loadTop250Movies() {
        _top250MoviesLiveData.postValue(AppState.Loading)
        viewModelScope.launch {
            try {
                val movies = getTop250MoviesUseCase()
                _top250MoviesLiveData.value = AppState.Success(movies)
            } catch (error: Throwable) {
                _top250MoviesLiveData.postValue(AppState.Error(error))
                Log.d("TAG VM 250 Movie", "${error.message.toString()}")
            }

        }
    }

    private fun loadTop250TVs() {
        _top250TVShowLiveData.postValue(AppState.Loading)
        viewModelScope.launch {
            try {
                val series = getTop250TvsUseCase()
                _top250TVShowLiveData.value = AppState.Success(series)
            } catch (error: Throwable) {
                _top250TVShowLiveData.postValue(AppState.Error(error))
                Log.d("TAG VM 250 TV", "${error.message.toString()}")
            }

        }
    }
}