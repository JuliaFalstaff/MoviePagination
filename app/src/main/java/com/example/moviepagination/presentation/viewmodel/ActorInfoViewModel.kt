package com.example.moviepagination.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.usecases.GetActorInfoByIdUseCase
import com.example.moviepagination.presentation.core.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ActorInfoViewModel(
    private val getActorInfoByIdUseCase: GetActorInfoByIdUseCase
) : BaseViewModel() {

    private val _actorLiveData: MutableLiveData<AppState> = MutableLiveData()
    val actorLiveData: LiveData<AppState> get() = _actorLiveData

    fun loadActorInfoById(actorId: String) {
        _actorLiveData.postValue(AppState.Loading)
        viewModelCustomScope.launch {
            getActorInfoByIdUseCase(actorId)
                .catch { error ->
                    _actorLiveData.postValue(AppState.Error(error))
                }
                .collect { actor ->
                    _actorLiveData.value = AppState.SuccessActorInfo(actor)
                }
        }
    }
}