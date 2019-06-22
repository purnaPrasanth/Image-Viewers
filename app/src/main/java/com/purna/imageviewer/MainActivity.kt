package com.purna.imageviewer

import androidx.recyclerview.widget.LinearLayoutManager
import com.purna.baseandroid.BaseActivity
import com.purna.baseandroid.InfiniteScrollListener
import com.purna.data.datasource.Error
import com.purna.data.datasource.Success
import com.purna.imageviewer.databinding.ActivityMainBinding
import com.purna.imageviewer.ext.showShortToast
import com.purna.imageviewer.generators.appDispatchersProvider
import com.purna.imageviewer.generators.imageListRepo
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val imageAdapter = ImagesRvAdapter(this)

    private val currentPage = AtomicInteger(0)
    private val isLoadingItems = AtomicBoolean(false)

    private val layoutManager = LinearLayoutManager(this)

    private val paginationScrollListener = object : InfiniteScrollListener(layoutManager) {
        override fun loadMoreItems() {
            getItems(currentPage.get() + 1, 30)
        }
    }

    override fun initUI() {
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = imageAdapter
        binding.recyclerView.addOnScrollListener(paginationScrollListener)
        binding.recyclerView.addItemDecoration(VerticalSpaceItemDecoration(32))
        getItems(0, 30)
    }

    private fun getItems(page: Int, perPage: Int) {
        if (!isLoadingItems.getAndSet(true)) {
            launch(appDispatchersProvider.getInstance().ioDispatcher) {
                when (val result = imageListRepo.getInstance().getImageList(page, perPage)) {
                    is Success -> {
                        launch(appDispatchersProvider.getInstance().mainDispatcher) {
                            val newItems = arrayListOf<String>().apply {
                                addAll(imageAdapter.getItems())
                                addAll(result.data.map { it.imageUrl })
                            }
                            imageAdapter.setData(newItems)
                        }
                        currentPage.set(page)
                        isLoadingItems.set(false)
                    }
                    is Error -> {
                        result.exception.printStackTrace()
                        showShortToast("Server Error")
                        isLoadingItems.set(false)
                    }
                }
            }
        }
    }
}
