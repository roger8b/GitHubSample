package br.com.rms.githubsample.domain

data class Repository(

    val id: String,
    val title: String,
    val description: String,
    val pullRequestCounter: Int,
    val starCounter: Int,
    val avatarUrl: String

)