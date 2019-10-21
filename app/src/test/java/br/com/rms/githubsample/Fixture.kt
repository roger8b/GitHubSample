package br.com.rms.githubsample

import br.com.rms.githubsample.domain.Repository

const val id: Long = 9999
const val title: String = "name"
const val description: String = "description"
const val starCounter: Long = 9999
const val avatarURL: String = "avatarURL"
const val login: String = "login"

const val query = "query"
const val sort = "sort"
const val order = "order"
const val page = 1

const val name: String = "name"

const val stargazersCount: Long = 9999
const val forksCount: Long = 9999


val validRepository = Repository(
    id,
    title,
    description,
    forksCount,
    starCounter,
    avatarURL,
    login
)

val validRepositoryList = listOf(validRepository)


