package br.com.rms.githubsample.base

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class CoroutineContextProvider {
    val main: CoroutineContext by lazy { Dispatchers.Main }
    val io: CoroutineContext by lazy { Dispatchers.IO }
}