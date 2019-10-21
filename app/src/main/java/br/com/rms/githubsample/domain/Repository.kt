package br.com.rms.githubsample.domain

data class Repository(

    val id: Long,
    val name: String,
    val description: String,
    val forksCount: Long,
    val stargazersCount: Long,
    val avatarURL: String,
    val login: String

)