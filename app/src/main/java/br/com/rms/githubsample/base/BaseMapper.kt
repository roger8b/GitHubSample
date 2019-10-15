package br.com.rms.githubsample.base

import arrow.core.Either
import br.com.rms.githubsample.domain.Repository

abstract class BaseMapper<input, output> {

    abstract fun map(element: input): Either<Throwable, output>
}