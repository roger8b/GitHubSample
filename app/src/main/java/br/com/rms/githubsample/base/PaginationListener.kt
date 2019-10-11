package br.com.rms.githubsample.base

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class PaginationScrollListener
    (var layoutManager: GridLayoutManager) : RecyclerView.OnScrollListener() {

    abstract fun loadMoreItems()

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
            loadMoreItems()
        }
    }
}