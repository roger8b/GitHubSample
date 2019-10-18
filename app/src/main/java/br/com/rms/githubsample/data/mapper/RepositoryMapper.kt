package br.com.rms.githubsample.data.mapper

import br.com.rms.githubsample.base.BaseMapper
import br.com.rms.githubsample.data.model.GitHubRepository
import br.com.rms.githubsample.domain.Repository

class RepositoryMapper : BaseMapper<GitHubRepository, Repository>() {

    override fun map(element: GitHubRepository): Repository {
        if (element.id == null) throw Throwable("Error repository Id is null")
        val id = element.id

        if (element.name == null) throw Throwable("Error repository Name is null")
        if (element.name.isEmpty()) throw Throwable("Error repository Name is Empty")
        val name = element.name

        val description: String =
            if (element.description.isNullOrEmpty()) "" else element.description

        if (element.forksCount == null) throw Throwable("Error repository Forks Count is null")
        val forksCount = element.forksCount

        if (element.stargazersCount == null) throw Throwable("Error repository Stargazers Count is null")
        val stargazersCount = element.stargazersCount

        if (element.owner == null) throw Throwable("Error repository Owner is null")
        if (element.owner.avatarURL == null) throw Throwable("Error repository Avatar URL is null")
        if (element.owner.avatarURL.isEmpty()) throw Throwable("Error repository Avatar URL is Empty")
        val avatarURL = element.owner.avatarURL

        return Repository(id, name, description, forksCount, stargazersCount, avatarURL)
    }

}
