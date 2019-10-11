package br.com.rms.githubsample.data.repository

import br.com.rms.githubsample.domain.SearchParameters
import br.com.rms.githubsample.domain.Repository

interface GitHubSearchRepositoryContract {

    fun search(params: SearchParameters): List<Repository>

}
