package br.com.rms.githubsample.usecases

import arrow.core.Either
import br.com.rms.githubsample.base.BaseUseCase
import br.com.rms.githubsample.data.repository.GitHubSearchRepositoryContract
import br.com.rms.githubsample.domain.Repository
import br.com.rms.githubsample.domain.SearchParameters
import br.com.rms.githubsample.log.Logs
import kotlinx.coroutines.CoroutineScope

class SearchRepositoryUseCase(

    private val repository: GitHubSearchRepositoryContract,
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
        invoke(
            coroutineScope,
            SearchParameters(query, sort, order, page), onResult
        )
    }

    override suspend fun run(params: SearchParameters): Either<Throwable, List<Repository>> {
        return try {
            val list = repository.search(params)
            Either.Right(list)
        } catch (e: Throwable) {
            Either.Left(e)
        }
    }
}