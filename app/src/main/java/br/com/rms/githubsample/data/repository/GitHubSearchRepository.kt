package br.com.rms.githubsample.data.repository

import br.com.rms.githubsample.data.mapper.RepositoryMapper
import br.com.rms.githubsample.data.model.GitHubRepository
import br.com.rms.githubsample.data.model.GitHubSearchResult
import br.com.rms.githubsample.data.remote.ApiService
import br.com.rms.githubsample.domain.Repository
import br.com.rms.githubsample.domain.SearchParameters
import br.com.rms.githubsample.log.Logs
import retrofit2.Response

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
        )
        return if (response.isSuccessful) {
            val repositoryList = validateResponse(response)
            if (repositoryList.isEmpty()) return listOf() else repositoryList.map { mapper.map(it) }
        } else {
            throw Throwable("Error ${response.code()} ${response.message()}")
        }
    }

    private fun validateResponse(response: Response<GitHubSearchResult>): List<GitHubRepository> {
        val gitHubSearchResult = response.body() ?: throw  NoSuchElementException("Response Body is Null")
        return gitHubSearchResult.items ?: throw NoSuchElementException("Repository list is null")
    }
}