package com.purna.imageviewer

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.purna.baseandroid.BaseActivity
import com.purna.baseandroid.InfiniteScrollListener
import com.purna.imageviewer.databinding.ActivityMainBinding
import com.purna.imageviewer.generators.appDispatchersProvider

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val imageAdapter = ImagesRvAdapter(this)

    private val imageViewModel by lazy {
        ViewModelProviders.of(
            this,
            ImageListViewModelFactory(appDispatchersProvider.getInstance())
        ).get(ImageListViewModel::class.java)
    }

    private val layoutManager by lazy { GridLayoutManager(this, 3) }

    private val paginationScrollListener by lazy {
        object : InfiniteScrollListener(layoutManager) {
            override fun loadMoreItems() {
                imageViewModel.fetchNextPage()
            }
        }
    }

    override fun initUI() {
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = imageAdapter
        binding.recyclerView.addOnScrollListener(paginationScrollListener)
        binding.recyclerView.addItemDecoration(VerticalSpaceItemDecoration(32))
    }

    override fun setListeners() {
        imageViewModel.listOFImages.observe(this, Observer { images ->
            imageAdapter.setData(images.orEmpty())
        })
    }
}
