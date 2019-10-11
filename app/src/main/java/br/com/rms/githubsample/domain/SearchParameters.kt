package br.com.rms.githubsample.domain

data class SearchParameters(
    val query: String,
    val sort: String,
    val order: String,
    val page: Int
)