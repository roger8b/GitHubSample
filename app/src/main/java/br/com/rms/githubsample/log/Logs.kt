package br.com.rms.githubsample.log

import timber.log.Timber

class Logs {

    private val tag = "GITHUB_SAMPLE"

    fun debug(message: String) {
        Timber.tag(tag).d(message)
    }

    fun error(message: String, e: Throwable) {
        Timber.tag(tag).e(e, message)
    }
}