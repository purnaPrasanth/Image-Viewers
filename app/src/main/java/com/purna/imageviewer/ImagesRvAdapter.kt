package com.purna.imageviewer

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import coil.api.load
import coil.request.LoadRequestBuilder
import com.purna.baseandroid.BaseHolder
import com.purna.baseandroid.SingleTypeBaseRvAdapter
import com.purna.data.entity.ImageListEntity
import com.purna.imageviewer.databinding.ItemListImageBinding
import com.purna.imageviewer.generators.coilImageLoader

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/

// RV Adapter for List of Images

class ImagesRvAdapter(context: Context) :
    SingleTypeBaseRvAdapter<ItemListImageBinding, ImageListEntity>(context, R.layout.item_list_image) {
    override fun onBindViewHolder(binding: ItemListImageBinding, position: Int) {
        val imageUrl = getItem(position).imageUrl
        coilImageLoader.getInstance().load(mContext, imageUrl) {
            lifecycle(mContext as LifecycleOwner)
            target(binding.image)
        }
    }

    override fun areItemsSame(oldItem: ImageListEntity, newItem: ImageListEntity) = oldItem.id == newItem.id

    override fun areContentsSame(oldItem: ImageListEntity, newItem: ImageListEntity) =
        oldItem.imageUrl == newItem.imageUrl

    override fun onViewRecycled(holder: BaseHolder<ItemListImageBinding>) {
        super.onViewRecycled(holder)
        holder.binding().image.setImageBitmap(null)
    }

}