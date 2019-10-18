package br.com.rms.githubsample.data.repository

import br.com.rms.githubsample.domain.Repository
import br.com.rms.githubsample.domain.SearchParameters

interface GitHubSearchRepositoryContract {

    suspend fun search(params: SearchParameters): List<Repository>

}
