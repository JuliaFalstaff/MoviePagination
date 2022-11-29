package com.example.moviepagination.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.usecases.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SavedMovieListViewModel(
    private val getAllSavedMoviesUseCase: GetAllSavedMoviesUseCase
) : ViewModel() {

    private val _savedMovieLiveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    val savedMoviesLiveData: LiveData<AppState> get() = _savedMovieLiveDataToObserve
    private val compositeDisposable = CompositeDisposable()

    fun loadSaveMovies() {
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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}