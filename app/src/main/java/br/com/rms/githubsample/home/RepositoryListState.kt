package br.com.rms.githubsample.home

import br.com.rms.githubsample.domain.Repository

sealed class RepositoryListState {

    class ShowResult(val result: MutableList<Repository>) : RepositoryListState()

    object ShowError : RepositoryListState()
}