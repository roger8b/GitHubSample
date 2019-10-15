package br.com.rms.githubsample.data.mapper

import arrow.core.Either
import br.com.rms.githubsample.base.BaseMapper
import br.com.rms.githubsample.data.model.GitHubElement
import br.com.rms.githubsample.domain.Repository

class RepositoryMapper : BaseMapper<GitHubElement, Repository>() {

    override fun map(element: GitHubElement): Either<Throwable, Repository> {
        if (element.id == null) return Either.Left(Throwable("Error element Id is null"))
        val id = element.id

        if (element.name == null) return Either.Left(Throwable("Error element Name is null"))
        if (element.name.isEmpty()) return Either.Left(Throwable("Error element Name is Empty"))
        val name = element.name

        if (element.description == null) return Either.Left(Throwable("Error element Description is null"))
        if (element.description.isEmpty()) return Either.Left(Throwable("Error element Description is Empty"))
        val description = element.description

        if (element.forksCount == null) return Either.Left(Throwable("Error element Forks Count is null"))
        val forksCount = element.forksCount

        if (element.stargazersCount == null) return Either.Left(Throwable("Error element Stargazers Count is null"))
        val stargazersCount = element.stargazersCount

        if (element.owner == null) return Either.Left(Throwable("Error element Owner is null"))
        if (element.owner.avatarURL == null) return Either.Left(Throwable("Error element Avatar URL is null"))
        if (element.description.isEmpty()) return Either.Left(Throwable("Error element Avatar URL is Empty"))
        val avatarURL = element.owner.avatarURL

        return Either.Right(
            Repository(
                id,
                name,
                description,
                forksCount,
                stargazersCount,
                avatarURL
            )
        )

    }

}
