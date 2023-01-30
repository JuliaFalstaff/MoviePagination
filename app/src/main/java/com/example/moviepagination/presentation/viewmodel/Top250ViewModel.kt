package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.GetTop250MoviesUseCase
import com.example.moviepagination.domain.usecases.GetTop250TvsUseCase
import com.example.moviepagination.presentation.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class Top250ViewModel(
    private val getTop250MoviesUseCase: GetTop250MoviesUseCase,
    private val getTop250TvsUseCase: GetTop250TvsUseCase
) : BaseViewModel() {

    private val _top250MoviesLiveData: MutableLiveData<AppState> = MutableLiveData()
    private val _top250TVShowLiveData: MutableLiveData<AppState> = MutableLiveData()
    val top250MoviesLiveData: LiveData<AppState> get() = _top250MoviesLiveData
    val top250TVShowLiveData: LiveData<AppState> get() = _top250TVShowLiveData

    fun loadTop250Movies() {
        viewModelScope.launch {
            getTop250MoviesUseCase()
                .onStart { _top250MoviesLiveData.postValue(AppState.Loading) }
                .catch { error ->
                    _top250MoviesLiveData.postValue(AppState.Error(error))
                }
                .onEach { movies ->
                    _top250MoviesLiveData.value = AppState.Success(movies)
                }.collect()
        }
    }

    fun loadTop250TVs() {
        viewModelScope.launch {
            getTop250TvsUseCase()
                .onStart { _top250TVShowLiveData.postValue(AppState.Loading) }
                .catch { error ->
                    _top250TVShowLiveData.postValue(AppState.Error(error))
                }
                .onEach { series ->
                    _top250TVShowLiveData.value = AppState.Success(series)
                }.collect()
        }
    }
}