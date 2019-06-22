package com.purna.imageviewer

import android.content.Context
import com.purna.baseandroid.SingleTypeBaseRvAdapter
import com.purna.imageviewer.databinding.ItemListImageBinding
import com.purna.imageviewer.generators.appDispatchersProvider
import com.purna.imageviewer.generators.imageLoader
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/
class ImagesRvAdapter(context: Context) :
    SingleTypeBaseRvAdapter<ItemListImageBinding, String>(context, R.layout.item_list_image) {
    override fun onBindViewHolder(binding: ItemListImageBinding, position: Int) {
        GlobalScope.launch(appDispatchersProvider.getInstance().commonDispatcher) {
            imageLoader.getInstance().loadImage(getItem(position), binding.image)
        }
    }

    override fun areItemsSame(oldItem: String, newItem: String) = oldItem == newItem

    override fun areContentsSame(oldItem: String, newItem: String) = oldItem == newItem

}