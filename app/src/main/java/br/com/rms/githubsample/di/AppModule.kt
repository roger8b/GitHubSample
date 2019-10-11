package br.com.rms.githubsample.di

import br.com.rms.githubsample.log.Logs
import br.com.rms.githubsample.home.RepositoryListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Logs() }
}

val viewModel = module {
    viewModel { RepositoryListViewModel(get()) }
}

val mNetworkModules = module {
}

private const val BASE_URL = "https://api.github.com/search/repositories/"
