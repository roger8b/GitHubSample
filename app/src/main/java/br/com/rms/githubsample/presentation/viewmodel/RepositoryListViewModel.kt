package br.com.rms.githubsample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rms.githubsample.base.CoroutineContextProvider
import br.com.rms.githubsample.base.ScreenState
import br.com.rms.githubsample.domain.Repository
import br.com.rms.githubsample.log.Logs
import br.com.rms.githubsample.usecases.SearchRepositoryUseCaseContract
import kotlinx.coroutines.launch

class RepositoryListViewModel(

    private val logs: Logs,
    private val useCase: SearchRepositoryUseCaseContract,
    private val coroutineContextProvider: CoroutineContextProvider

) : ViewModel() {

    sealed class State {
        class UpdateRepositoryList(val result: List<Repository>) : State()
        object ShowError : State()
    }

    private val _state: MutableLiveData<ScreenState<State>> = MutableLiveData()

    val state: LiveData<ScreenState<State>>
        get() = _state

    fun fetchRepositoryList(query: String, sort: String, order: String, page: Int) {
        viewModelScope.launch(coroutineContextProvider.main) {
            _state.value = ScreenState.ShowLoading
            useCase.getTopRepositoryList(query, sort, order, page, this) {
                this.fold(::handleFailure, ::handleSuccess)
            }
        }
    }

    private fun handleSuccess(list: List<Repository>) {
        _state.value = ScreenState.HideLoading
        _state.value = ScreenState.Render(State.UpdateRepositoryList(list))
    }

    private fun handleFailure(throwable: Throwable) {
        logs.error("REPOSITORY LIST VIEW MODEL", throwable)
        _state.value = ScreenState.Render(State.ShowError)
    }
}