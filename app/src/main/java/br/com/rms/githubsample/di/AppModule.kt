package br.com.rms.githubsample.di

import br.com.rms.githubsample.data.repository.GitHubSearchRepository
import br.com.rms.githubsample.data.repository.GitHubSearchRepositoryContract
import br.com.rms.githubsample.log.Logs
import br.com.rms.githubsample.presentation.viewmodel.SearchRepositoryViewModel
import br.com.rms.githubsample.usecases.TopRepositoryUseCase
import br.com.rms.githubsample.usecases.TopRepositoryUseCaseContract
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<GitHubSearchRepositoryContract> { GitHubSearchRepository() }
    single<TopRepositoryUseCaseContract> {
        TopRepositoryUseCase(
            get(),
            get()
        )
    }
    single { Logs() }
}

val viewModel = module {
    viewModel {
        SearchRepositoryViewModel(
            get(),
            get()
        )
    }
}

val mNetworkModules = module {
}

private const val BASE_URL = "https://api.github.com/search/repositories/"
