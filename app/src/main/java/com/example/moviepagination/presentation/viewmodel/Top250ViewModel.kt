package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.GetTop250MoviesUseCase
import com.example.moviepagination.domain.usecases.GetTop250TvsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class Top250ViewModel(
    private val getTop250MoviesUseCase: GetTop250MoviesUseCase,
    private val getTop250TvsUseCase: GetTop250TvsUseCase
) : ViewModel() {

    private val _top250MoviesLiveData: MutableLiveData<AppState> = MutableLiveData()
    private val _top250TVShowLiveData: MutableLiveData<AppState> = MutableLiveData()
    val top250MoviesLiveData: LiveData<AppState> get() = _top250MoviesLiveData
    val top250TVShowLiveData: LiveData<AppState> get() = _top250TVShowLiveData
    private val compositeDisposable = CompositeDisposable()

    init {
        loadTop250Movies()
        loadTop250TVs()
    }

    private fun loadTop250Movies() {
        _top250MoviesLiveData.postValue(AppState.Loading)
        val disposable = getTop250MoviesUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _top250MoviesLiveData.postValue(AppState.Success(it))
            }, {
                _top250MoviesLiveData.postValue(AppState.Error(it))
            })
        compositeDisposable.add(disposable)
    }

    private fun loadTop250TVs() {
        _top250TVShowLiveData.postValue(AppState.Loading)
        val disposable = getTop250TvsUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _top250TVShowLiveData.postValue(AppState.Success(it))
            }, {
                _top250TVShowLiveData.postValue(AppState.Error(it))
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}