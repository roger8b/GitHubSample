package br.com.rms.githubsample.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rms.githubsample.base.ScreenState
import br.com.rms.githubsample.log.Logs
import br.com.rms.githubsample.domain.Repository

class RepositoryListViewModel(

    val logs: Logs

) : ViewModel() {

    private val _state: MutableLiveData<ScreenState<RepositoryListState>> = MutableLiveData()

    val state: LiveData<ScreenState<RepositoryListState>>
        get() = _state

    fun fetchRepositoryList() {
        val list = mutableListOf<Repository>()

        repeat((1..3).count()) {
            list.add(

                Repository(
                    (it * 10).toString(),
                    "Mussum Ipsum, cacilds vidis litro abertis. Mauris nec dolor in eros commodo tempor.",
                    "Mussum Ipsum, cacilds vidis litro abertis. Mauris nec dolor in eros commodo tempor. Aenean aliquam molestie leo, vitae iaculis nisl. Delegadis gente finis, bibendum egestas augue arcu ut est",
                    100, 100,
                    ""
                )
            )
            list.add(
                Repository(
                    it.toString(),
                    "Mussum Ipsum, cacilds",
                    "Mussum Ipsum, cacilds vidis litro abertis.",
                    100, 100,
                    ""
                )
            )


        }
        _state.value = ScreenState.Render(RepositoryListState.ShowResult(list))
    }
}