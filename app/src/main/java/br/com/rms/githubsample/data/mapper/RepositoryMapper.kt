package br.com.rms.githubsample.data.mapper

import br.com.rms.githubsample.base.BaseMapper
import br.com.rms.githubsample.data.model.GitHubRepository
import br.com.rms.githubsample.domain.Repository

class RepositoryMapper : BaseMapper<GitHubRepository, Repository>() {

    override fun map(repository: GitHubRepository): Repository {
        if (repository.id == null) throw Throwable("Error repository Id is null")
        val id = repository.id

        if (repository.name == null) throw Throwable("Error repository Name is null")
        if (repository.name.isEmpty()) throw Throwable("Error repository Name is Empty")
        val name = repository.name

        if (repository.description == null) throw Throwable("Error repository Description is null")
        if (repository.description.isEmpty()) throw Throwable("Error repository Description is Empty")
        val description = repository.description

        if (repository.forksCount == null) throw Throwable("Error repository Forks Count is null")
        val forksCount = repository.forksCount

        if (repository.stargazersCount == null) throw Throwable("Error repository Stargazers Count is null")
        val stargazersCount = repository.stargazersCount

        if (repository.owner == null) throw Throwable("Error repository Owner is null")
        if (repository.owner.avatarURL == null) throw Throwable("Error repository Avatar URL is null")
        if (repository.owner.avatarURL.isEmpty()) throw Throwable("Error repository Avatar URL is Empty")
        val avatarURL = repository.owner.avatarURL

        return Repository(id, name, description, forksCount, stargazersCount, avatarURL)
    }

}
