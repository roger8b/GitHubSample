package br.com.rms.githubsample.data.repository

import br.com.rms.githubsample.data.mapper.RepositoryMapper
import br.com.rms.githubsample.data.remote.ApiService
import br.com.rms.githubsample.domain.Repository
import br.com.rms.githubsample.domain.SearchParameters
import br.com.rms.githubsample.log.Logs

class GitHubSearchRepository(

    private val service: ApiService,
    private val mapper: RepositoryMapper,
    private val logs: Logs

) :
    GitHubSearchRepositoryContract {

    override suspend fun search(params: SearchParameters): List<Repository> {
        logs.debug("SEARCH REPOSITORY")
        val response = service.searchRepository(
            hashMapOf(
                Pair("q", params.query),
                Pair("sort", params.sort),
                Pair("page", params.page.toString()),
                Pair("order", params.order)
            )
        ).body()

        return response?.items?.map { mapper.map(it) } ?: listOf()

    }
}