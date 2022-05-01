package com.example.moviepagination.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepagination.model.AppState
import com.example.moviepagination.model.repository.ILocalRepo
import com.example.moviepagination.model.repository.IRemoteRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieListViewModel(
        val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
        val remoteRepo: IRemoteRepo,
        val localRepo: ILocalRepo,
) : ViewModel() {

    fun getLiveData(): LiveData<AppState> = liveDataToObserve
    private val compositeDisposable = CompositeDisposable()

    fun loadMovieListData() {
        val disposable = remoteRepo.getComingSoonMoviesFromServer()
                .doAfterSuccess { localRepo.saveMovieList(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    liveDataToObserve.postValue(AppState.Success(it))
                }, {
                    liveDataToObserve.postValue(AppState.Error(it))

                })
        compositeDisposable.add(disposable)
    }

    private fun getFromLocalDB() {
        val disposableLocal = localRepo.getAllMovieList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            liveDataToObserve.postValue(AppState.Success(it))
                        }, {
                    liveDataToObserve.postValue(AppState.Error(it))
                })
        compositeDisposable.add(disposableLocal)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}