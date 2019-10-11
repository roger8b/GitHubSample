package br.com.rms.githubsample.di

import br.com.rms.githubsample.base.CoroutineContextProvider
import br.com.rms.githubsample.data.repository.GitHubSearchRepository
import br.com.rms.githubsample.data.repository.GitHubSearchRepositoryContract
import br.com.rms.githubsample.log.Logs
import br.com.rms.githubsample.presentation.viewmodel.SearchRepositoryViewModel
import br.com.rms.githubsample.usecases.SearchRepositoryUseCase
import br.com.rms.githubsample.usecases.SearchRepositoryUseCaseContract
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<GitHubSearchRepositoryContract> { GitHubSearchRepository() }

    single<SearchRepositoryUseCaseContract> { SearchRepositoryUseCase(get(), get()) }

    single { Logs() }

    single { CoroutineContextProvider() }
}

val viewModel = module {
    viewModel {
        SearchRepositoryViewModel(
            get(),
            get(),
            get()
        )
    }
}

val mNetworkModules = module {
}

private const val BASE_URL = "https://api.github.com/search/repositories/"
