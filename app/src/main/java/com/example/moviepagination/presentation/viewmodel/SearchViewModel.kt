package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.GetSearchListUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchViewModel(
    private val getSearchListUseCase: GetSearchListUseCase
) : ViewModel() {

    private val _searchMovieLiveData: MutableLiveData<AppState> = MutableLiveData()
    val searchMovieLiveData: LiveData<AppState> get() = _searchMovieLiveData
    private val compositeDisposable = CompositeDisposable()

    fun loadSearchResultFromApi(expression: String) {
        _searchMovieLiveData.postValue(AppState.Loading)
        val disposable = getSearchListUseCase(expression)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _searchMovieLiveData.postValue(AppState.SuccessSearchResult(it))
                }, {
                    _searchMovieLiveData.postValue(AppState.Error(it))

                })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}