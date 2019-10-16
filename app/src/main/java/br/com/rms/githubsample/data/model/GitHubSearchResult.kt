package br.com.rms.githubsample.data.model

import com.google.gson.annotations.SerializedName

data class GitHubSearchResult(
    @SerializedName("total_count") val total_count: Long? = null,
    @SerializedName("incomplete_results") val incomplete_results: Boolean? = null,
    @SerializedName("items") val items: List<GitHubRepository>? = null
)

