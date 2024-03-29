package br.com.rms.githubsample.usecases

import arrow.core.Either
import br.com.rms.githubsample.base.BaseUseCase
import br.com.rms.githubsample.base.CoroutineContextProvider
import br.com.rms.githubsample.data.repository.GitHubSearchRepositoryContract
import br.com.rms.githubsample.domain.Repository
import br.com.rms.githubsample.domain.SearchParameters
import br.com.rms.githubsample.log.Logs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

class SearchRepositoryUseCase(

    private val repository: GitHubSearchRepositoryContract,
    private val coroutineContextProvider: CoroutineContextProvider,
    private val logs: Logs

) :
    SearchRepositoryUseCaseContract, BaseUseCase<List<Repository>, SearchParameters>() {

    override suspend fun getTopRepositoryList(
        query: String,
        sort: String,
        order: String,
        page: Int,
        coroutineScope: CoroutineScope,
        onResult: Either<Throwable, List<Repository>>.() -> Unit
    ) {
        logs.debug("GET TOP REPOSITORY LIST HAS CALLED: \nQUERY: $query,\nSORT: $sort,\nORDER: $order, \nPAGE: $page")
        invoke(coroutineScope, SearchParameters(query, sort, order, page), onResult)
    }


    override suspend fun run(params: SearchParameters): Either<Throwable, List<Repository>> {
        return withContext(coroutineContextProvider.io) {
            try {
                Either.Right(repository.search(checkParameters(params)))
            } catch (e: Throwable) {
                Either.Left(e)
            }
        }
    }

    private fun checkParameters(params: SearchParameters): SearchParameters = params.apply {
        if (query.isEmpty()) throw Throwable("Query parameter is Empty")
        if (sort.isEmpty()) throw Throwable("Sort parameter is Empty")
        if (order.isEmpty()) throw Throwable("Query parameter is Empty")
    }
}
