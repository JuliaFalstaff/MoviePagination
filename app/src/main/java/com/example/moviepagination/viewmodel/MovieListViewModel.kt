package com.example.moviepagination.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepagination.model.AppState
import com.example.moviepagination.model.repository.ILocalRepo
import com.example.moviepagination.model.repository.IRemoteRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieListViewModel(
    val remoteRepo: IRemoteRepo,
    val localRepo: ILocalRepo,
) : ViewModel() {

//    init {
//        loadComingSoonMovies()
//        loadMostPopularMovies()
//        loadMostPopularTVs()
//        loadMoviesNowInTheatre()
//    }


    private val nowInTheatreLiveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val comingSoonLiveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val popularMoviesLiveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val popularTVsLiveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getNowInTheatreLiveData(): LiveData<AppState> = nowInTheatreLiveDataToObserve
    fun getComingSoonLiveData(): LiveData<AppState> = comingSoonLiveDataToObserve
    fun getPopularMoviesLiveData(): LiveData<AppState> = popularMoviesLiveDataToObserve
    fun getPopularTVsLiveData(): LiveData<AppState> = popularTVsLiveDataToObserve

    private val compositeDisposable = CompositeDisposable()

    fun loadComingSoonMovies() {
        val disposable = remoteRepo.getComingSoonMoviesFromServer()
            .doAfterSuccess { localRepo.saveMovieList(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                comingSoonLiveDataToObserve.postValue(AppState.Success(it))
            }, {
                comingSoonLiveDataToObserve.postValue(AppState.Error(it))

            })
        compositeDisposable.add(disposable)
    }

    fun loadMoviesNowInTheatre() {
        val disposable = remoteRepo.getMovieNowInTheatre()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    nowInTheatreLiveDataToObserve.postValue(AppState.Success(it))
                }, {
                    nowInTheatreLiveDataToObserve.postValue(AppState.Error(it))

                })
        compositeDisposable.add(disposable)
    }

    fun loadMostPopularMovies() {
        val disposable = remoteRepo.getMostPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    popularMoviesLiveDataToObserve.postValue(AppState.Success(it))
                }, {
                    popularMoviesLiveDataToObserve.postValue(AppState.Error(it))

                })
        compositeDisposable.add(disposable)
    }

    fun loadMostPopularTVs() {
        val disposable = remoteRepo.getMostPopularTVs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    popularTVsLiveDataToObserve.postValue(AppState.Success(it))
                }, {
                    popularTVsLiveDataToObserve.postValue(AppState.Error(it))

                })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}