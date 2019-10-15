package br.com.rms.githubsample.data.mapper

import br.com.rms.githubsample.*
import br.com.rms.githubsample.data.model.GitHubElement
import br.com.rms.githubsample.data.model.Owner
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import kotlin.test.assertEquals

class RepositoryMapperTest {

    @Mock
    lateinit var gitHubElement: GitHubElement

    @Mock
    lateinit var owner: Owner

    lateinit var mapper: RepositoryMapper

    @Before
    fun setup() {
        initMocks(this)

        mapper = RepositoryMapper()

        whenever(gitHubElement.id).thenReturn(id)
        whenever(gitHubElement.name).thenReturn(name)
        whenever(gitHubElement.description).thenReturn(description)
        whenever(gitHubElement.forksCount).thenReturn(forksCount)
        whenever(gitHubElement.stargazersCount).thenReturn(stargazersCount)
        whenever(gitHubElement.owner).thenReturn(owner)
        whenever(gitHubElement.owner?.avatarURL).thenReturn(avatarURL)
    }

    @Test
    fun `When all GitHubElement fields are valid the function should return an object of type Repository`() {

        val mapperResponse = mapper.map(gitHubElement)

        mapperResponse.map { repository ->
            assertEquals(id, repository.id)
            assertEquals(name, repository.name)
            assertEquals(description, repository.description)
            assertEquals(forksCount, repository.forksCount)
            assertEquals(stargazersCount, repository.stargazersCount)
            assertEquals(avatarURL, repository.avatarURL)
        }
    }

    @Test
    fun `When Id is null, the Either Left should return an exception`() {
        whenever(gitHubElement.id).thenReturn(null)

        mapper.map(gitHubElement).mapLeft { error ->
            assertEquals("Error element Id is null", error.message)
        }
    }

    @Test
    fun `When the name is null, the Either Left should return an exception`() {
        whenever(gitHubElement.name).thenReturn(null)

        mapper.map(gitHubElement).mapLeft { error ->
            assertEquals("Error element Name is null", error.message)
        }
    }

    @Test
    fun `When the name is empty, the Either Left should return an exception`() {
        whenever(gitHubElement.name).thenReturn("")

        mapper.map(gitHubElement).mapLeft { error ->
            assertEquals("Error element Name is Empty", error.message)
        }
    }

    @Test
    fun `When the description is null, the Either Left should return an exception`() {
        whenever(gitHubElement.description).thenReturn(null)

        mapper.map(gitHubElement).mapLeft { error ->
            assertEquals("Error element Description is null", error.message)
        }
    }

    @Test
    fun `When the description is empty, the Either Left should return an exception`() {
        whenever(gitHubElement.description).thenReturn("")

        mapper.map(gitHubElement).mapLeft { error ->
            assertEquals("Error element Description is Empty", error.message)
        }
    }

    @Test
    fun `When the forksCount is null, the Either Left should return an exception`() {
        whenever(gitHubElement.forksCount).thenReturn(null)

        mapper.map(gitHubElement).mapLeft { error ->
            assertEquals("Error element Forks Count is null", error.message)
        }
    }

    @Test
    fun `When the stargazersCount is null, the Either Left should return an exception`() {
        whenever(gitHubElement.stargazersCount).thenReturn(null)

        mapper.map(gitHubElement).mapLeft { error ->
            assertEquals("Error element Stargazers Count is null", error.message)
        }
    }

    @Test
    fun `When the owner is null, the Either Left should return an exception`() {
        whenever(gitHubElement.owner).thenReturn(null)

        mapper.map(gitHubElement).mapLeft { error ->
            assertEquals("Error element Owner is null", error.message)
        }
    }

    @Test
    fun `When the avatarURL is null, the Either Left should return an exception`() {
        whenever(gitHubElement.owner?.avatarURL).thenReturn(null)

        mapper.map(gitHubElement).mapLeft { error ->
            assertEquals("Error element Avatar URL is null", error.message)
        }
    }

    @Test
    fun `When the avatarURL is empty, the Either Left should return an exception`() {
        whenever(gitHubElement.owner?.avatarURL).thenReturn("")

        mapper.map(gitHubElement).mapLeft { error ->
            assertEquals("Error element Avatar URL is Empty", error.message)
        }
    }
}