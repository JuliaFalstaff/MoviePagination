package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.GetComingSoonMovieUseCase
import com.example.moviepagination.domain.usecases.GetMostPopularMoviesUseCase
import com.example.moviepagination.domain.usecases.GetMostPopularTVsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieListViewModel(
    private val getComingSoonUseCase: GetComingSoonMovieUseCase,
    private val getPopularMoviesUseCase: GetMostPopularMoviesUseCase,
    private val getMostPopularTVsUseCase: GetMostPopularTVsUseCase
) : ViewModel() {

    private val _comingSoonLiveData: MutableLiveData<AppState> = MutableLiveData()
    private val _popularMoviesLiveData: MutableLiveData<AppState> = MutableLiveData()
    private val _popularTVsLiveData: MutableLiveData<AppState> = MutableLiveData()
    val comingSoonLiveData: LiveData<AppState> get() = _comingSoonLiveData
    val popularMoviesLiveData: LiveData<AppState> get() = _popularMoviesLiveData
    val popularTVsLiveData: LiveData<AppState> get() = _popularTVsLiveData
    private val compositeDisposable = CompositeDisposable()

    init {
        loadComingSoonMovies()
//        loadMostPopularMovies()
//        loadMostPopularTVs()
    }

    private fun loadComingSoonMovies() {
        _comingSoonLiveData.postValue(AppState.Loading)
        val disposable = getComingSoonUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _comingSoonLiveData.postValue(AppState.Success(it))
            }, {
                _comingSoonLiveData.postValue(AppState.Error(it))

            })
        compositeDisposable.add(disposable)
    }

    private fun loadMostPopularMovies() {
        _popularMoviesLiveData.postValue(AppState.Loading)
        val disposable = getPopularMoviesUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _popularMoviesLiveData.postValue(AppState.Success(it))
            }, {
                _popularMoviesLiveData.postValue(AppState.Error(it))

            })
        compositeDisposable.add(disposable)
    }

    private fun loadMostPopularTVs() {
        _popularTVsLiveData.postValue(AppState.Loading)
        val disposable = getMostPopularTVsUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _popularTVsLiveData.postValue(AppState.Success(it))
            }, {
                _popularTVsLiveData.postValue(AppState.Error(it))

            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}