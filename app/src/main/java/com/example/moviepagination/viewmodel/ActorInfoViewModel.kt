package com.example.moviepagination.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviepagination.model.AppState
import com.example.moviepagination.model.repository.IRemoteRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ActorInfoViewModel(
        val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
        val remoteRepo: IRemoteRepo,
) : ViewModel() {

    fun getActorInfoLiveData(): LiveData<AppState> = liveDataToObserve
    private val compositeDisposable = CompositeDisposable()

    fun loadActorInfoById(actorId: String) {
        val disposable = remoteRepo.getActorInfoById(actorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    liveDataToObserve.postValue(AppState.SuccessActorInfo(it))
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