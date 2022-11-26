package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.repository.IRemoteRepo
import com.example.moviepagination.domain.usecases.GetSearchListUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchViewModel(
    private val getSearchListUseCase: GetSearchListUseCase
) : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    fun getSearchResultLiveData(): LiveData<AppState> = liveDataToObserve
    private val compositeDisposable = CompositeDisposable()

    fun loadSearchResultFromApi(expression: String) {
        val disposable = getSearchListUseCase(expression)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    liveDataToObserve.postValue(AppState.SuccessSearchResult(it))
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