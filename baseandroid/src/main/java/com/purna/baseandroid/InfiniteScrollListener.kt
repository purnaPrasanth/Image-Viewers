package com.purna.baseandroid

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

/**
 * Infinite Scroll Listener for introducing pagination into [RecyclerView]
 *
 * @param layoutManager Layout Manager used with recycler view
 */

abstract class InfiniteScrollListener(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            val totalItem = layoutManager.itemCount
            val currentVisibleItems = layoutManager.childCount
            val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if ((currentVisibleItems + firstVisibleItem) >= totalItem
                && firstVisibleItem >= 0
            ) {
                loadMoreItems()
            }
        }
    }

    /**
     * callback to request more items into the [RecyclerView]
     */
    abstract fun loadMoreItems()
}