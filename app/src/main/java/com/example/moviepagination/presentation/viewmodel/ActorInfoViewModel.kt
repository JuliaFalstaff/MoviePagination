package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.GetActorInfoByIdUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ActorInfoViewModel(
    private val getActorInfoByIdUseCase: GetActorInfoByIdUseCase
) : ViewModel() {

    private val _actorLiveData: MutableLiveData<AppState> = MutableLiveData()
    val actorLiveData: LiveData<AppState> get() = _actorLiveData
    private val compositeDisposable = CompositeDisposable()

    fun loadActorInfoById(actorId: String) {
        _actorLiveData.postValue(AppState.Loading)
        val disposable = getActorInfoByIdUseCase(actorId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _actorLiveData.postValue(AppState.SuccessActorInfo(it))
            }, {
                _actorLiveData.postValue(AppState.Error(it))
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}