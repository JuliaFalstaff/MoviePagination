package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieListViewModel(
    private val getComingSoonUseCase: GetComingSoonMovieUseCase,
    private val getNowInTheatreUseCase: GetMovieNowInTheatreUseCase,
    private val getPopularMoviesUseCase: GetMostPopularMoviesUseCase,
    private val getMostPopularTVsUseCase: GetMostPopularTVsUseCase
) : ViewModel() {

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
        comingSoonLiveDataToObserve.postValue(AppState.Loading)
        val disposable = getComingSoonUseCase()
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
        comingSoonLiveDataToObserve.postValue(AppState.Loading)
        val disposable = getNowInTheatreUseCase()
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
        comingSoonLiveDataToObserve.postValue(AppState.Loading)
        val disposable = getMostPopularTVsUseCase()
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
        comingSoonLiveDataToObserve.postValue(AppState.Loading)
        val disposable = getMostPopularTVsUseCase()
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