package br.com.rms.githubsample.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.rms.githubsample.*
import br.com.rms.githubsample.data.mapper.RepositoryMapper
import br.com.rms.githubsample.data.model.GitHubRepository
import br.com.rms.githubsample.data.model.GitHubSearchResult
import br.com.rms.githubsample.data.model.Owner
import br.com.rms.githubsample.data.remote.ApiService
import br.com.rms.githubsample.domain.SearchParameters
import br.com.rms.githubsample.log.Logs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GitHubSearchRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var service: ApiService

    @Mock
    lateinit var mapper: RepositoryMapper

    @Mock
    lateinit var logs: Logs

    @Mock
    lateinit var gitHubRepository: GitHubRepository

    @Mock
    lateinit var gitHubSearchResult: GitHubSearchResult

    @Mock
    lateinit var owner: Owner

    @Mock
    lateinit var searchParameters: SearchParameters

    private lateinit var repository: GitHubSearchRepository

    @Before
    fun setup() {
        initMocks(this)
        repository = GitHubSearchRepository(service, mapper, logs)

        whenever(searchParameters.order).thenReturn(order)
        whenever(searchParameters.page).thenReturn(page)
        whenever(searchParameters.query).thenReturn(query)
        whenever(searchParameters.sort).thenReturn(sort)

        whenever(gitHubRepository.id).thenReturn(id)
        whenever(gitHubRepository.name).thenReturn(name)
        whenever(gitHubRepository.description).thenReturn(description)
        whenever(gitHubRepository.forksCount).thenReturn(forksCount)
        whenever(gitHubRepository.stargazersCount).thenReturn(stargazersCount)
        whenever(gitHubRepository.owner).thenReturn(owner)
        whenever(gitHubRepository.owner?.avatarURL).thenReturn(avatarURL)

        whenever(gitHubSearchResult.items).thenReturn(listOf(gitHubRepository))
    }

    @Test
    fun `When all parameters are correct and the service call is successful, you should return a list of repositories`() {
        runBlocking(Dispatchers.Unconfined) {

            whenever(service.searchRepository(anyOfType())).thenReturn(
                Response.success(
                    gitHubSearchResult
                )
            )

            whenever(mapper.map(anyOfType())).thenReturn(validRepository)

            val searchResult = repository.search(searchParameters)

            assertEquals(id, searchResult.first().id)
            assertEquals(name, searchResult.first().name)
            assertEquals(description, searchResult.first().description)
            assertEquals(forksCount, searchResult.first().forksCount)
            assertEquals(stargazersCount, searchResult.first().stargazersCount)
            assertEquals(avatarURL, searchResult.first().avatarURL)
        }
    }

    @Test
    fun `When the service call returns success and Body is empty an exception should be returned`() {
        runBlocking(Dispatchers.Unconfined) {
            whenever(service.searchRepository(anyOfType())).thenReturn(Response.success(null))

            assertFailsWith(NoSuchElementException::class, "Response Body is Null") {
                repository.search(searchParameters)
            }
        }
    }

    @Test
    fun `When the service call returns success and the repository list is nulla should return an exception`() {
        runBlocking(Dispatchers.Unconfined) {
            whenever(service.searchRepository(anyOfType())).thenReturn(
                Response.success(
                    gitHubSearchResult
                )
            )

            whenever(gitHubSearchResult.items).thenReturn(null)

            assertFailsWith(NoSuchElementException::class, "Repository list is null") {
                repository.search(searchParameters)
            }
        }
    }

    @Test
    fun `When all parameters are correct and service call results in error, an exception should be returned`() {
        runBlocking(Dispatchers.Unconfined) {
            val responseBody = ResponseBody.create(
                MediaType.get("application/json; charset=utf-8"),
                "{\"message\":\"Not Found\",\"documentation_url\":\"https://developer.github.com/v3\"}"
            )

            whenever(service.searchRepository(anyOfType())).thenReturn(
                Response.error(
                    404,
                    responseBody
                )
            )

            assertFailsWith(Throwable::class) {
                repository.search(searchParameters)
            }

        }
    }
}