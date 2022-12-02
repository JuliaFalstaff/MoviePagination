package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieListNowInTheatreViewModel(
    private val getNowInTheatreUseCase: GetMovieNowInTheatreUseCase
) : ViewModel() {

    private val _nowInTheatre: MutableLiveData<AppState> = MutableLiveData()

    val nowInTheatre: LiveData<AppState>
    get() = _nowInTheatre

    private val compositeDisposable = CompositeDisposable()

    fun loadMoviesNowInTheatre() {
        _nowInTheatre.postValue(AppState.Loading)
        val disposable = getNowInTheatreUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _nowInTheatre.postValue(AppState.Success(it))
                }, {
                    _nowInTheatre.postValue(AppState.Error(it))

                })
        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}