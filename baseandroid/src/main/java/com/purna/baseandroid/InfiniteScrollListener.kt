package com.purna.baseandroid

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/
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

    abstract fun loadMoreItems()
}