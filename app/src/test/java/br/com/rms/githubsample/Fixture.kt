package br.com.rms.githubsample

import br.com.rms.githubsample.domain.Repository
import br.com.rms.githubsample.domain.SearchParameters

const val id: Long = 9999
const val title: String = "name"
const val description: String = "description"
const val starCounter: Long = 9999
const val avatarURL: String = "avatarURL"

const val query = "query"
const val sort = "sort"
const val order = "order"
const val page = 1

val validRepository = Repository(
    id,
    title,
    description,
    forksCount,
    starCounter,
    avatarURL
)

val validSearchParameters = SearchParameters(
    query,
    sort,
    order,
    page
)

val validRepositoryList = listOf(validRepository)


