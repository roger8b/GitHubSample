package br.com.rms.githubsample.data.remote

import br.com.rms.githubsample.data.model.GitHubSearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface ApiService {

    @GET("search/repositories")
    suspend fun searchRepository(@QueryMap options: Map<String, String>): Response<GitHubSearchResult>
}
