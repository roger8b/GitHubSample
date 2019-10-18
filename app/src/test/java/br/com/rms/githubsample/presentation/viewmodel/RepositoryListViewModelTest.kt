package br.com.rms.githubsample.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import arrow.core.Either
import br.com.rms.githubsample.*
import br.com.rms.githubsample.base.CoroutineContextProvider
import br.com.rms.githubsample.base.ScreenState
import br.com.rms.githubsample.domain.Repository
import br.com.rms.githubsample.log.Logs
import br.com.rms.githubsample.usecases.SearchRepositoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class RepositoryListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var useCase: SearchRepositoryUseCase

    @Mock
    lateinit var logs: Logs


    @Mock
    lateinit var observer: Observer<ScreenState<RepositoryListViewModel.State>>

    @Mock
    lateinit var coroutineContextProvider: CoroutineContextProvider

    @Mock
    lateinit var lifecycleOwner: LifecycleOwner
    lateinit var lifecycle: Lifecycle

    lateinit var listViewModel: RepositoryListViewModel


    @Before
    fun setup() {
        initMocks(this)
        listViewModel = RepositoryListViewModel(logs, useCase, coroutineContextProvider)
        lifecycle = LifecycleRegistry(lifecycleOwner)
        listViewModel.state.observeForever(observer)

        whenever(coroutineContextProvider.io).thenReturn(Dispatchers.Unconfined)
        whenever(coroutineContextProvider.main).thenReturn(Dispatchers.Unconfined)
    }

    @Test
    fun `When all search parameters are correct and the service does not return error the success state should be called by returning a list of repositories`() {
        runBlocking {
            whenever(
                useCase.getTopRepositoryList(
                    anyString(),
                    anyString(),
                    anyString(),
                    anyInt(),
                    anyOfType(),
                    anyOfType()
                )
            ).thenAnswer {
                val function = it.arguments[5] as Either<Throwable, List<Repository>>.() -> Unit
                function(Either.Right(validRepositoryList))
            }

            listViewModel.fetchRepositoryList(query, sort, order, page)

            assertEquals(
                (((listViewModel.state.value as (ScreenState.Render)).renderState) as RepositoryListViewModel.State.UpdateRepositoryList).result,
                validRepositoryList
            )
        }
    }

    @Test
    fun `When all search parameters are correct and the service returns an error the error status should be called`() {
        runBlocking {
            whenever(
                useCase.getTopRepositoryList(
                    anyString(),
                    anyString(),
                    anyString(),
                    anyInt(),
                    anyOfType(),
                    anyOfType()
                )
            ).thenAnswer {
                val function = it.arguments[5] as Either<Throwable, List<Repository>>.() -> Unit
                function(Either.Left(Throwable()))
            }

            listViewModel.fetchRepositoryList(query, sort, order, page)

            assertTrue {
                val render = listViewModel.state.value as ScreenState.Render
                render.renderState is RepositoryListViewModel.State.ShowError
            }
        }
    }
}
