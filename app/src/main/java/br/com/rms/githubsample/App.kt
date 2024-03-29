package br.com.rms.githubsample

import androidx.multidex.MultiDexApplication
import br.com.rms.githubsample.di.appModule
import br.com.rms.githubsample.di.mNetworkModules
import br.com.rms.githubsample.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    viewModel,
                    mNetworkModules
                )
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}