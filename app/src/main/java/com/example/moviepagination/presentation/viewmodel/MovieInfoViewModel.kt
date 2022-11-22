package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.repository.IRemoteRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieInfoViewModel(
    val remoteRepo: IRemoteRepo
) : ViewModel() {

    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    val liveDataTrailerToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getDetailedLiveData(): LiveData<AppState> = liveDataToObserve
    fun getTrailerLiveData(): LiveData<AppState> = liveDataTrailerToObserve
    private val compositeDisposable = CompositeDisposable()

    fun loadMovieById(movieId: String) {
        val disposable = remoteRepo.getMovieByIdFromServer(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveDataToObserve.postValue(AppState.SuccessMovieInfo(it))
            }, {
                liveDataToObserve.postValue(AppState.Error(it))
            })
        compositeDisposable.add(disposable)
    }

    fun loadMovieTrailer(movieId: String) {
        val disposable = remoteRepo.getMovieTrailerById(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveDataTrailerToObserve.postValue(AppState.SuccessTrailer(it))
            }, {
                liveDataTrailerToObserve.postValue(AppState.Error(it))
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}