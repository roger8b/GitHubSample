package br.com.rms.githubsample.data.repository

import br.com.rms.githubsample.domain.SearchParameters
import br.com.rms.githubsample.domain.Repository

class GitHubSearchRepository : GitHubSearchRepositoryContract {

    override fun search(params: SearchParameters): List<Repository> {
        val list = mutableListOf<Repository>()
        repeat((1..3).count()) {
            list.add(
                Repository(
                    (it * 10).toString(),
                    "Mussum Ipsum, cacilds vidis litro abertis. Mauris nec dolor in eros commodo tempor.",
                    "Mussum Ipsum, cacilds vidis litro abertis. Mauris nec dolor in eros commodo tempor. Aenean aliquam molestie leo, vitae iaculis nisl. Delegadis gente finis, bibendum egestas augue arcu ut est",
                    100, 100,
                    ""
                )
            )
            list.add(
                Repository(
                    it.toString(),
                    "Mussum Ipsum, cacilds",
                    "Mussum Ipsum, cacilds vidis litro abertis.",
                    100, 100,
                    ""
                )
            )
        }

        return list
    }
}