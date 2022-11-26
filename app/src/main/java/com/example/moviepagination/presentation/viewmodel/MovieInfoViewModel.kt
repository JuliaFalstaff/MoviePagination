package com.example.moviepagination.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.usecases.GetMovieByIdUseCase
import com.example.moviepagination.domain.usecases.GetMoviesTrailerUseCase
import com.example.moviepagination.domain.usecases.SaveMovieToMyListUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieInfoViewModel(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val getMoviesTrailerUseCase: GetMoviesTrailerUseCase,
    private val saveMovieToMyListUseCase: SaveMovieToMyListUseCase
) : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val liveDataTrailerToObserve: MutableLiveData<AppState> = MutableLiveData()
    fun getDetailedLiveData(): LiveData<AppState> = liveDataToObserve
    fun getTrailerLiveData(): LiveData<AppState> = liveDataTrailerToObserve
    private val compositeDisposable = CompositeDisposable()

    fun loadMovieById(movieId: String) {
        val disposable = getMovieByIdUseCase(movieId)
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
        val disposable = getMoviesTrailerUseCase(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveDataTrailerToObserve.postValue(AppState.SuccessTrailer(it))
            }, {
                liveDataTrailerToObserve.postValue(AppState.Error(it))
            })
        compositeDisposable.add(disposable)
    }

    fun saveMovieToMyList(movie: MovieInfo) {
        val disposable = saveMovieToMyListUseCase(movie)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    saveMovieToMyListUseCase(movie)
                },
                {
                    Log.d("TAG", "Error save movie ${it.localizedMessage}")
                }
            )
        compositeDisposable.add(disposable)

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}