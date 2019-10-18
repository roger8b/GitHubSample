package br.com.rms.githubsample.base

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class EndlessRecyclerViewScrollListener(layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    private var visibleThreshold = 10
    private var currentPage = 1
    private var previousTotalItemCount = 1
    private val startingPageIndex = 2
    private var loading = true

    private var mLayoutManager: RecyclerView.LayoutManager = layoutManager

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = mLayoutManager.itemCount

        when (mLayoutManager) {
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions =
                    (mLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
            }
            is GridLayoutManager -> lastVisibleItemPosition =
                (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()
            is LinearLayoutManager -> lastVisibleItemPosition =
                (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        }

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount

        }
        if (loading && totalItemCount > previousTotalItemCount) {
            previousTotalItemCount = totalItemCount
        }

        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {

            onLoadMore(currentPage, totalItemCount, view)
        }
    }

    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView)

    fun nexPage(){
        currentPage++
    }

    fun getCurrentPage() = currentPage

    fun onLoading() {
        loading = true
    }

    fun hideLoading() {
        loading = false
    }
}
