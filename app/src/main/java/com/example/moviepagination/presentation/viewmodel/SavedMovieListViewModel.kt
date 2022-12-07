package com.example.moviepagination.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.GetAllSavedMoviesUseCase
import com.example.moviepagination.presentation.core.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SavedMovieListViewModel(
    private val getAllSavedMoviesUseCase: GetAllSavedMoviesUseCase
) : BaseViewModel() {

    private val _savedMovieLiveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    val savedMoviesLiveData: LiveData<AppState> get() = _savedMovieLiveDataToObserve

    init {
        loadSaveMovies()
    }

    private fun loadSaveMovies() {
        _savedMovieLiveDataToObserve.postValue(AppState.Loading)
        val disposable = getAllSavedMoviesUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _savedMovieLiveDataToObserve.postValue(AppState.SuccessMovieInfoList(it))
            }, {
                _savedMovieLiveDataToObserve.postValue(AppState.Error(it))
                Log.d("Tag Save VM", "${it.localizedMessage}")
            })
        compositeDisposable.add(disposable)
    }
}