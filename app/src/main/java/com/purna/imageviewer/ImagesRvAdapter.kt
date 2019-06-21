package com.purna.imageviewer

import android.content.Context
import android.graphics.BitmapFactory
import com.purna.httpclient.HttpClient
import com.purna.imageloadercore.ImageDataLoader
import com.purna.baseandroid.SingleTypeBaseRvAdapter
import com.purna.imageviewer.databinding.ItemListImageBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/
class ImagesRvAdapter(context: Context) :
    SingleTypeBaseRvAdapter<ItemListImageBinding, String>(context, R.layout.item_list_image) {
    override fun onBindViewHolder(binding: ItemListImageBinding, position: Int) {
        GlobalScope.launch {
            val imageLoader = ImageDataLoader(
                ImageViewerApplication.appDispatchersProvider.getInstance().ioDispatcher,
                HttpClient(ImageViewerApplication.appDispatchersProvider.getInstance().ioDispatcher)
            )

            val bitmap =
                BitmapFactory.decodeStream(
                    imageLoader.loadImageData(
                        URL(getItem(position))
                    )
                )
            launch(ImageViewerApplication.appDispatchersProvider.getInstance().mainDispatcher) {
                binding.image.setImageBitmap(bitmap)
            }
        }
    }

    override fun areItemsSame(oldItem: String, newItem: String) = oldItem == newItem

    override fun areContentsSame(oldItem: String, newItem: String) = oldItem == newItem

}