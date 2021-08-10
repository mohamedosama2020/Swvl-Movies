package com.moham.swvlmoviewithflicker.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.moham.swvlmoviewithflicker.R
import com.moham.swvlmoviewithflicker.data.entities.flickrPhoto.Photo
import java.io.IOException
import java.nio.charset.StandardCharsets

fun loadJSONFromAsset(context: Context, fileName: String?): String? {
    try {
        val inputStream = context.assets.open(fileName!!)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, StandardCharsets.UTF_8)
    } catch (ex: IOException) {
        ex.printStackTrace()
    }
    return null
}

fun getFlickrImageURL(context: Context, photo: Photo): String =
    context.getString(R.string.image_url, photo.server, photo.id, photo.secret)


fun loadImage(context: Context,photo: Photo,imageView:ImageView,onPhotoLoaded:()->Unit){
    Glide.with(context)
        .load(getFlickrImageURL(context, photo))
        .listener(object : RequestListener<Drawable?> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any,
                target: Target<Drawable?>,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
            override fun onResourceReady(
                resource: Drawable?,
                model: Any,
                target: Target<Drawable?>,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
               onPhotoLoaded()
                return false
            }
        }).apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).into(imageView)
}



