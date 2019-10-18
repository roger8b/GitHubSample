package br.com.rms.githubsample.data.mapper

import br.com.rms.githubsample.*
import br.com.rms.githubsample.data.model.GitHubRepository
import br.com.rms.githubsample.data.model.Owner
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RepositoryMapperTest {

    @Mock
    lateinit var gitHubRepository: GitHubRepository

    @Mock
    lateinit var owner: Owner

    lateinit var mapper: RepositoryMapper

    @Before
    fun setup() {
        initMocks(this)

        mapper = RepositoryMapper()

        whenever(gitHubRepository.id).thenReturn(id)
        whenever(gitHubRepository.name).thenReturn(name)
        whenever(gitHubRepository.description).thenReturn(description)
        whenever(gitHubRepository.forksCount).thenReturn(forksCount)
        whenever(gitHubRepository.stargazersCount).thenReturn(stargazersCount)
        whenever(gitHubRepository.owner).thenReturn(owner)
        whenever(gitHubRepository.owner?.avatarURL).thenReturn(avatarURL)
    }

    @Test
    fun `When all GitHubElement fields are valid the function should return an object of type Repository`() {

        val mapperResponse = mapper.map(gitHubRepository)

        assertEquals(id, mapperResponse.id)
        assertEquals(name, mapperResponse.name)
        assertEquals(description, mapperResponse.description)
        assertEquals(forksCount, mapperResponse.forksCount)
        assertEquals(stargazersCount, mapperResponse.stargazersCount)
        assertEquals(avatarURL, mapperResponse.avatarURL)
    }

    @Test
    fun `When Id is null, the should return an exception`() {
        whenever(gitHubRepository.id).thenReturn(null)

        assertFailsWith(Throwable::class, "Error repository Id is null") {
            mapper.map(gitHubRepository)
        }
    }

    @Test
    fun `When the name is null, the should return an exception`() {
        whenever(gitHubRepository.name).thenReturn(null)
        assertFailsWith(Throwable::class, "Error repository Name is null") {
            mapper.map(gitHubRepository)
        }
    }

    @Test
    fun `When the name is empty, the should return an exception`() {
        whenever(gitHubRepository.name).thenReturn("")

        assertFailsWith(Throwable::class, "Error repository Name is Empty") {
            mapper.map(gitHubRepository)
        }
    }

    @Test
    fun `When the description is null, the should return an exception`() {
        whenever(gitHubRepository.description).thenReturn(null)

        val repository = mapper.map(gitHubRepository)

        assertEquals("", repository.description)
    }

    @Test
    fun `When the description is empty, the should return an exception`() {
        whenever(gitHubRepository.description).thenReturn("")

        val repository = mapper.map(gitHubRepository)

        assertEquals("", repository.description)
    }

    @Test
    fun `When the forksCount is null, the should return an exception`() {
        whenever(gitHubRepository.forksCount).thenReturn(null)

        assertFailsWith(Throwable::class, "Error repository Forks Count is null") {
            mapper.map(gitHubRepository)
        }
    }

    @Test
    fun `When the stargazersCount is null, the should return an exception`() {
        whenever(gitHubRepository.stargazersCount).thenReturn(null)

        assertFailsWith(Throwable::class, "Error repository Stargazers Count is null") {
            mapper.map(gitHubRepository)
        }
    }

    @Test
    fun `When the owner is null, the should return an exception`() {
        whenever(gitHubRepository.owner).thenReturn(null)

        assertFailsWith(Throwable::class, "Error repository Owner is null") {
            mapper.map(gitHubRepository)
        }
    }

    @Test
    fun `When the avatarURL is null, the should return an exception`() {
        whenever(gitHubRepository.owner?.avatarURL).thenReturn(null)

        assertFailsWith(Throwable::class, "Error repository Avatar URL is null") {
            mapper.map(gitHubRepository)
        }
    }

    @Test
    fun `When the avatarURL is empty, the should return an exception`() {
        whenever(gitHubRepository.owner?.avatarURL).thenReturn("")

        assertFailsWith(Throwable::class, "Error repository Avatar URL is Empty") {
            mapper.map(gitHubRepository)
        }
    }
}