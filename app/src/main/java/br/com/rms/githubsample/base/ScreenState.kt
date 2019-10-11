package br.com.rms.githubsample.base

sealed class ScreenState<out T> {

    object ShowLoading : ScreenState<Nothing>()
    object HideLoading : ScreenState<Nothing>()


    class Render<T>(val renderState: T): ScreenState<T>()
}