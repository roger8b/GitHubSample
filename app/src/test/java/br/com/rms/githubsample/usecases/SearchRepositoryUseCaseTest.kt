package br.com.rms.githubsample.usecases

import br.com.rms.githubsample.*
import br.com.rms.githubsample.base.CoroutineContextProvider
import br.com.rms.githubsample.data.repository.GitHubSearchRepository
import br.com.rms.githubsample.domain.Repository
import br.com.rms.githubsample.log.Logs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SearchRepositoryUseCaseTest {

    @Mock
    lateinit var logs: Logs

    @Mock
    lateinit var coroutineContextProvider: CoroutineContextProvider

    @Mock
    lateinit var repository: GitHubSearchRepository


    lateinit var useCase: SearchRepositoryUseCaseContract

    lateinit var coroutineScope: CoroutineScope

    @Before
    fun setup() {
        initMocks(this)
        coroutineScope = CoroutineScope(Dispatchers.Unconfined)
        useCase = SearchRepositoryUseCase(repository, coroutineContextProvider, logs)

        whenever(coroutineContextProvider.io).thenReturn(Dispatchers.Unconfined)
        whenever(coroutineContextProvider.main).thenReturn(Dispatchers.Unconfined)

    }

    @Test
    fun `When all fields are submitted and the service returns success the onResult callback should return a list`() {
        runBlocking {
            whenever(repository.search(anyOfType())).thenReturn(validRepositoryList)

            var list: List<Repository> = listOf()

            useCase.getTopRepositoryList(
                query,
                sort,
                order,
                page,
                coroutineScope
            ) { this.map { list = it } }

            assertEquals(list, validRepositoryList)

        }
    }

    @Test
    fun `When all fields are submitted and the service returns an error, the onResult callback should return an exception`() {
        runBlocking {
            whenever(repository.search(anyOfType())).thenThrow(RuntimeException("error"))
            var error: Throwable? = null

            useCase.getTopRepositoryList(
                query,
                sort,
                order,
                page,
                coroutineScope
            ) {
                this.mapLeft { error = it }
            }

            assertNotNull(error)
        }
    }

    @Test
    fun `When the query field is empty the callback onResult should return an error`() {
        runBlocking {

            var error: Throwable? = null

            useCase.getTopRepositoryList(
                "",
                sort,
                order,
                page,
                coroutineScope
            ) {
                this.mapLeft { error = it }
            }

            assertNotNull(error)
        }
    }

    @Test
    fun `When the sort field is empty the onResult callback should return an error`() {
        runBlocking {

            var error: Throwable? = null

            useCase.getTopRepositoryList(
                query,
                "",
                order,
                page,
                coroutineScope
            ) {
                this.mapLeft { error = it }
            }

            assertNotNull(error)
        }
    }

    @Test
    fun `When the order field is empty the onResult callback should return an error`() {
        runBlocking {

            var error: Throwable? = null

            useCase.getTopRepositoryList(
                query,
                sort,
                "",
                page,
                coroutineScope
            ) {
                this.mapLeft { error = it }
            }

            assertNotNull(error)
        }
    }
}


