package br.com.rms.githubsample.data.model

import com.google.gson.annotations.SerializedName

data class GitHubRepository(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("node_id") val notId: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("full_name") val fullName: String? = null,
    @SerializedName("private") val private: Boolean? = null,
    @SerializedName("owner") val owner: Owner? = null,
    @SerializedName("html_url") val htmlURL: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("fork") val fork: Boolean? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("forks_url") val forksURL: String? = null,
    @SerializedName("keys_url") val keysURLs: String? = null,
    @SerializedName("collaborators_url") val collaboratorsURL: String? = null,
    @SerializedName("teams_url") val teamsURL: String? = null,
    @SerializedName("hooks_url") val hooksURL: String? = null,
    @SerializedName("issue_events_url") val issueEventsURL: String? = null,
    @SerializedName("events_url") val eventsURL: String? = null,
    @SerializedName("assignees_url") val assigneesURL: String? = null,
    @SerializedName("branches_url") val branchesURL: String? = null,
    @SerializedName("tags_url") val tagsURL: String? = null,
    @SerializedName("blobs_url") val blobsURL: String? = null,
    @SerializedName("git_tags_url") val gitTagsURL: String? = null,
    @SerializedName("git_refs_url") val gitRefsURL: String? = null,
    @SerializedName("trees_url") val treesURL: String? = null,
    @SerializedName("statuses_url") val statusesURL: String? = null,
    @SerializedName("languages_url") val languagesURL: String? = null,
    @SerializedName("stargazers_url") val stargazersURL: String? = null,
    @SerializedName("contributors_url") val contributorsURL: String? = null,
    @SerializedName("subscribers_url") val subscribersURL: String? = null,
    @SerializedName("subscription_url") val subscriptionURL: String? = null,
    @SerializedName("commits_url") val commitsURL: String? = null,
    @SerializedName("git_commits_url") val git_commitsURL: String? = null,
    @SerializedName("comments_url") val commentsURL: String? = null,
    @SerializedName("issue_comment_url") val issue_commentURL: String? = null,
    @SerializedName("contents_url") val contentsURL: String? = null,
    @SerializedName("compare_url") val compareURL: String? = null,
    @SerializedName("merges_url") val mergesURL: String? = null,
    @SerializedName("archive_url") val archiveURL: String? = null,
    @SerializedName("downloads_url") val downloadsURL: String? = null,
    @SerializedName("issues_url") val issuesURL: String? = null,
    @SerializedName("pulls_url") val pullsURL: String? = null,
    @SerializedName("milestones_url") val milestonesURL: String? = null,
    @SerializedName("notifications_url") val notificationsURL: String? = null,
    @SerializedName("labels_url") val labelsURL: String? = null,
    @SerializedName("releases_url") val releasesURL: String? = null,
    @SerializedName("deployments_url") val deploymentsURL: String? = null,
    @SerializedName("created_at") val createdAt: String? = null,
    @SerializedName("updated_at") val updatedAt: String? = null,
    @SerializedName("pushed_at") val pushedAt: String? = null,
    @SerializedName("git_url") val gitURL: String? = null,
    @SerializedName("ssh_url") val sshURL: String? = null,
    @SerializedName("clone_url") val cloneURL: String? = null,
    @SerializedName("svn_url") val svnURL: String? = null,
    @SerializedName("homepage") val homepage: String? = null,
    @SerializedName("size") val size: Long? = null,
    @SerializedName("stargazers_count") val stargazersCount: Long? = null,
    @SerializedName("watchers_count") val watchersCount: Long? = null,
    @SerializedName("language") val language: String? = null,
    @SerializedName("has_issues") val hasIssues: Boolean? = null,
    @SerializedName("has_projects") val hasProjects: Boolean? = null,
    @SerializedName("has_downloads") val hasDownloads: Boolean? = null,
    @SerializedName("has_wiki") val hasWiki: Boolean? = null,
    @SerializedName("has_pages") val hasPages: Boolean? = null,
    @SerializedName("forks_count") val forksCount: Long? = null,
    @SerializedName("mirror_url") val mirrorURL: String? = null,
    @SerializedName("archived") val archived: Boolean? = null,
    @SerializedName("disabled") val disabled: Boolean? = null,
    @SerializedName("open_issues_count") val openIssuesCount: Long? = null,
    @SerializedName("license") val license: License? = null,
    @SerializedName("forks") val forks: Long? = null,
    @SerializedName("open_issues") val openIssues: Long? = null,
    @SerializedName("watchers") val watchers: Long? = null,
    @SerializedName("default_branch") val defaultBranch: String? = null,
    @SerializedName("score") val score: Long? = null
)

data class License(
    @SerializedName("key") val key: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("spdx_id") val spdxId: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("node_id") val nodeId: String? = null
)

data class Owner(

    @SerializedName("login") val login: String? = null,
    @SerializedName("id") val id: Long? = null,
    @SerializedName("node_id") val nodeId: String? = null,
    @SerializedName("avatar_url") val avatarURL: String? = null,
    @SerializedName("gravatar_id") val gravatarId: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("html_url") val htmlURL: String? = null,
    @SerializedName("followers_url") val followersURL: String? = null,
    @SerializedName("following_url") val followingURL: String? = null,
    @SerializedName("gists_url") val gistsURL: String? = null,
    @SerializedName("starred_url") val starredURL: String? = null,
    @SerializedName("subscriptions_url") val subscriptionsURL: String? = null,
    @SerializedName("organizations_url") val organizationsURL: String? = null,
    @SerializedName("repos_url") val reposURL: String? = null,
    @SerializedName("events_url") val eventsURL: String? = null,
    @SerializedName("received_events_url") val receivedEventsURL: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("site_admin") val siteAdmin: Boolean? = null
)