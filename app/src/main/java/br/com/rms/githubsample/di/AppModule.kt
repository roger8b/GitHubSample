package br.com.rms.githubsample.di

import br.com.rms.githubsample.base.CoroutineContextProvider
import br.com.rms.githubsample.data.mapper.RepositoryMapper
import br.com.rms.githubsample.data.remote.ApiService
import br.com.rms.githubsample.data.remote.createNetworkClient
import br.com.rms.githubsample.data.repository.GitHubSearchRepository
import br.com.rms.githubsample.data.repository.GitHubSearchRepositoryContract
import br.com.rms.githubsample.log.Logs
import br.com.rms.githubsample.presentation.viewmodel.RepositoryListViewModel
import br.com.rms.githubsample.usecases.SearchRepositoryUseCase
import br.com.rms.githubsample.usecases.SearchRepositoryUseCaseContract
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {

    single { RepositoryMapper() }

    single<GitHubSearchRepositoryContract> { GitHubSearchRepository(get(), get(), get()) }

    single<SearchRepositoryUseCaseContract> { SearchRepositoryUseCase(get(), get(), get()) }

    single { Logs() }

    single { CoroutineContextProvider() }
}

val viewModel = module {
    viewModel {
        RepositoryListViewModel(
            get(),
            get(),
            get()
        )
    }
}

val mNetworkModules = module {
    single { createNetworkClient(BASE_URL) }
    single { (get() as Retrofit).create(ApiService::class.java) }
}

private const val BASE_URL = "https://api.github.com/"
