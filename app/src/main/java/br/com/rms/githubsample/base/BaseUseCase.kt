package br.com.rms.githubsample.base

import arrow.core.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Throwable, Type>

    open operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: Either<Throwable, Type>.() -> Unit = {}
    ) {
        val backGroundJob = scope.async { run(params) }
        scope.launch { onResult(backGroundJob.await()) }
    }


}