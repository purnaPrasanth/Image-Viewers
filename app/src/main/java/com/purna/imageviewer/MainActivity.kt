package com.purna.imageviewer

import androidx.recyclerview.widget.LinearLayoutManager
import com.purna.baseandroid.BaseActivity
import com.purna.data.datasource.Error
import com.purna.data.datasource.Success
import com.purna.imageviewer.databinding.ActivityMainBinding
import com.purna.imageviewer.ext.showShortToast
import com.purna.imageviewer.generators.appDispatchersProvider
import com.purna.imageviewer.generators.imageListRepo
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val imageAdapter = ImagesRvAdapter(this)

    override fun initUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = imageAdapter
        binding.recyclerView.addItemDecoration(VerticalSpaceItemDecoration(32))

        launch(appDispatchersProvider.getInstance().ioDispatcher) {

            when (val result = imageListRepo.getInstance().getImageList(0, 30)) {
                is Success -> {
                    launch(appDispatchersProvider.getInstance().mainDispatcher) {
                        imageAdapter.setData(
                            result.data.map { it.imageUrl }
                        )
                    }
                }
                is Error -> {
                    result.exception.printStackTrace()
                    showShortToast("Server Error")
                }
            }
        }
    }
}
