package com.purna.imageviewer

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purna.base.Dispatchers
import com.purna.baseandroid.BaseViewModel
import com.purna.data.datasource.Error
import com.purna.data.datasource.Success
import com.purna.data.entity.ImageListEntity
import com.purna.imageviewer.generators.imageListRepo
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by Purna on 2019-06-26 as a part of Image-Viewers
 **/
class ImageListViewModel(appDispatchers: Dispatchers, application: Application) :
    BaseViewModel(appDispatchers, application) {

    private val _listOfImages = MutableLiveData<List<ImageListEntity>>()

    val listOFImages: LiveData<List<ImageListEntity>>
        get() = _listOfImages

    private val currentPage = AtomicInteger(0)

    private val isLoading = AtomicBoolean(false)

    init {
        getItems(0, 30)
    }

    private fun getItems(page: Int, perPage: Int) {
        if (!isLoading.getAndSet(true)) {
            launch(appDispatchers.ioDispatcher) {
                when (val result = imageListRepo.getInstance().getImageList(page, perPage)) {
                    is Success -> {
                        val existingList = mutableListOf<ImageListEntity>().apply {
                            addAll(listOFImages.value.orEmpty())
                            addAll(result.data)
                        }
                        _listOfImages.postValue(existingList)
                        currentPage.set(page)
                        isLoading.set(false)
                    }
                    is Error -> {
                        result.exception.printStackTrace()
                        isLoading.set(false)
                    }
                }
            }
        }
    }

    fun fetchNextPage() {
        getItems(currentPage.get() + 1, 30)
    }
}

class ImageListViewModelFactory(val appDispatchers: Dispatchers) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImageListViewModel(appDispatchers, ImageViewerApplication.application) as T
    }
}