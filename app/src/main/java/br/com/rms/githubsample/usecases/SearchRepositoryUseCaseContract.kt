package br.com.rms.githubsample.usecases

import arrow.core.Either
import br.com.rms.githubsample.domain.Repository
import kotlinx.coroutines.CoroutineScope

interface SearchRepositoryUseCaseContract {
    suspend fun getTopRepositoryList(
        query: String,
        sort: String,
        order: String,
        page: Int,
        coroutineScope: CoroutineScope,
        onResult: Either<Throwable, List<Repository>>.() -> Unit = {}
    )
}
