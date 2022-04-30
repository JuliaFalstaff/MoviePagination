package com.example.moviepagination.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepagination.model.AppState
import com.example.moviepagination.model.repository.ILocalRepo
import com.example.moviepagination.model.repository.IRemoteRepo
import com.example.moviepagination.model.repository.LocalRepoImpl
import com.example.moviepagination.model.repository.RemoteRepoImpl
import com.example.moviepagination.model.retrofit.ApiService
import com.example.moviepagination.model.retrofit.RetrofitImpl
import com.example.moviepagination.model.room.MovieDataBase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieListViewModel(
        val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
        val remoteRepo: IRemoteRepo = RemoteRepoImpl(RetrofitImpl().api),
//        val localRepo: ILocalRepo = LocalRepoImpl()
) : ViewModel() {

    fun getLiveData(): LiveData<AppState> = liveDataToObserve
    private val compositeDisposable = CompositeDisposable()

    fun loadMovieListData() {
        val disposable = remoteRepo.getMovieListFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("MOVIE", it.toString())
                }, {
                    Log.d("MOVIE", it.message.toString())
                })

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}