package com.example.moviepagination.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepagination.model.AppState
import com.example.moviepagination.model.repository.IRemoteRepo
import com.example.moviepagination.model.repository.RemoteRepoImpl
import com.example.moviepagination.model.retrofit.RetrofitImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieInfoViewModel(
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    val remoteRepo: IRemoteRepo = RemoteRepoImpl(RetrofitImpl().api),
) : ViewModel() {

    fun getDetailedLiveData(): LiveData<AppState> = liveDataToObserve
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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}