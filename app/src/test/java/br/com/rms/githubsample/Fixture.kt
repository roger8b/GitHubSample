package br.com.rms.githubsample

import br.com.rms.githubsample.data.model.License
import br.com.rms.githubsample.data.model.Owner
import br.com.rms.githubsample.domain.Repository
import br.com.rms.githubsample.domain.SearchParameters

const val id: Long = 9999
const val title: String = "name"
const val description: String = "description"
const val starCounter: Long = 9999
const val avatarURL: String = "avatarURL"

const val query = "query"
const val sort = "sort"
const val order = "order"
const val page = 1

const val nodeID: String = "nodeID"
const val name: String = "name"
const val fullName: String = "fullName"
const val private: Boolean = false

const val htmlURL: String = "htmlURL"
const val fork: Boolean = false
const val url: String = "url"
const val forksURL: String = "forksURL"
const val keysURL: String = "keysURL"
const val collaboratorsURL: String = "collaboratorsURL"
const val teamsURL: String = "teamsURL"
const val hooksURL: String = "hooksURL"
const val issueEventsURL: String = "issueEventsURL"
const val eventsURL: String = "eventsURL"
const val assigneesURL: String = "assigneesURL"
const val branchesURL: String = "branchesURL"
const val tagsURL: String = "tagsURL"
const val blobsURL: String = "blobsURL"
const val gitTagsURL: String = "gitTagsURL"
const val gitRefsURL: String = "gitRefsURL"
const val treesURL: String = "treesURL"
const val statusesURL: String = "statusesURL"
const val languagesURL: String = "languagesURL"
const val stargazersURL: String = "stargazersURL"
const val contributorsURL: String = "contributorsURL"
const val subscribersURL: String = "subscribersURL"
const val subscriptionURL: String = "subscriptionURL"
const val commitsURL: String = "commitsURL"
const val gitCommitsURL: String = "gitCommitsURL"
const val commentsURL: String = " commentsURL"
const val issueCommentURL: String = "issueCommentURL"
const val contentsURL: String = "contentsURL"
const val compareURL: String = "compareURL"
const val mergesURL: String = "mergesURL"
const val archiveURL: String = "archiveURL"
const val downloadsURL: String = "downloadsURL"
const val issuesURL: String = "issuesURL"
const val pullsURL: String = "pullsURL"
const val milestonesURL: String = "milestonesURL"
const val notificationsURL: String = "notificationsURL"
const val labelsURL: String = "labelsURL"
const val releasesURL: String = "releasesURL"
const val deploymentsURL: String = "deploymentsURL"
const val createdAt: String = "createdAt"
const val updatedAt: String = "updatedAt"
const val pushedAt: String = "pushedAt"
const val gitURL: String = "gitURL"
const val sshURL: String = "sshURL"
const val cloneURL: String = "cloneURL"
const val svnURL: String = "svnURL"
const val homepage: String = "homepage"
const val size: Long = 9999
const val stargazersCount: Long = 9999
const val watchersCount: Long = 9999
const val language: String = "language"
const val hasIssues: Boolean = false
const val hasProjects: Boolean = false
const val hasDownloads: Boolean = false
const val hasWiki: Boolean = false
const val hasPages: Boolean = false
const val forksCount: Long = 9999
const val mirrorURL: String = "mirrorURL"
const val archived: Boolean = false
const val disabled: Boolean = false
const val openIssuesCount: Long = 9999
const val forks: Long = 9999
const val openIssues: Long = 9999
const val watchers: Long = 9999
const val defaultBranch: String = "defaultBranch"

const val key: String = "key"
const val spdxID: String = "spdxID"


const val login: String = "login"
const val gravatarID: String = "gravatarID"
const val followersURL: String = "followersURL"
const val followingURL: String = "followingURL"
const val gistsURL: String = "gistsURL"
const val starredURL: String = "starredURL"
const val subscriptionsURL: String = "subscriptionsURL"
const val organizationsURL: String = "organizationsURL"
const val reposURL: String = "reposURL"
const val receivedEventsURL: String = "receivedEventsURL"
const val type: String = "type"
const val siteAdmin: Boolean = false

const val admin: Boolean = false
const val push: Boolean = false
const val pull: Boolean = false


val license: License = License(key, name, spdxID, url, nodeID)

val owner: Owner = Owner(
    login, id, nodeID, avatarURL, gravatarID, url, htmlURL, followersURL,
    followingURL, gistsURL, starredURL, subscriptionsURL, organizationsURL, reposURL, eventsURL,
    receivedEventsURL, type, siteAdmin
)

val validRepository = Repository(
    id,
    title,
    description,
    forksCount,
    starCounter,
    avatarURL
)

val validSearchParameters = SearchParameters(
    query,
    sort,
    order,
    page
)

val validRepositoryList = listOf(validRepository)


