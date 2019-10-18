package br.com.rms.githubsample.base

import br.com.rms.githubsample.domain.Repository

abstract class BaseMapper<input, output> {

    abstract fun map(element: input): Repository
}