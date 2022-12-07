package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.GetSearchListUseCase
import com.example.moviepagination.presentation.core.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchViewModel(
    private val getSearchListUseCase: GetSearchListUseCase
) : BaseViewModel() {

    private val _searchMovieLiveData: MutableLiveData<AppState> = MutableLiveData()
    val searchMovieLiveData: LiveData<AppState> get() = _searchMovieLiveData

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
}