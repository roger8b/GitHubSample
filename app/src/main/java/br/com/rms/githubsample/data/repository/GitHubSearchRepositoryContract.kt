package br.com.rms.githubsample.data.repository

import arrow.core.Either
import br.com.rms.githubsample.domain.SearchParameters
import br.com.rms.githubsample.domain.Repository
import io.ktor.client.features.ResponseException

interface GitHubSearchRepositoryContract {

    suspend fun search(params: SearchParameters): List<Repository>

}
