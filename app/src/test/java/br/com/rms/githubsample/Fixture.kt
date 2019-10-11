package br.com.rms.githubsample

import br.com.rms.githubsample.domain.Repository
import br.com.rms.githubsample.domain.SearchParameters

const val id = "id"
const val title = "title"
const val description = "description"
const val pullRequestCounte = 9999
const val starCounter = 9999
const val avatarUrl = "avatarUrl"

const val query = "query"
const val sort = "sort"
const val order = "order"
const val page = 1

val validRepository = Repository(
    id,
    title,
    description,
    pullRequestCounte,
    starCounter,
    avatarUrl
)

val validSearchParameters = SearchParameters(
    query,
    sort,
    order,
    page
)

val validRepositoryList = listOf(validRepository)


