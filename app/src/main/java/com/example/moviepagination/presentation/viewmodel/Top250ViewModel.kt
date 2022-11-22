package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.repository.ILocalRepo
import com.example.moviepagination.domain.repository.IRemoteRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class Top250ViewModel(
    val remoteRepo: IRemoteRepo,
    val localRepo: ILocalRepo,
) : ViewModel() {

    private val top250MoviesLiveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val top250TVShowLiveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    fun getTop250MoviesLiveData(): LiveData<AppState> = top250MoviesLiveDataToObserve
    fun getTopTVShowMoviesLiveData(): LiveData<AppState> = top250TVShowLiveDataToObserve

    private val compositeDisposable = CompositeDisposable()

    fun loadTop250Movies() {
        val disposable = remoteRepo.getTOP250Movies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    top250MoviesLiveDataToObserve.postValue(AppState.Success(it))
                }, {
                    top250MoviesLiveDataToObserve.postValue(AppState.Error(it))
                })
        compositeDisposable.add(disposable)
    }

    fun loadTop250TVs() {
        val disposable = remoteRepo.getTOP250TVs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    top250TVShowLiveDataToObserve.postValue(AppState.Success(it))
                }, {
                    top250TVShowLiveDataToObserve.postValue(AppState.Error(it))
                })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}