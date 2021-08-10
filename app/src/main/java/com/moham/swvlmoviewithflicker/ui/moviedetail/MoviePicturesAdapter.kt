package com.moham.swvlmoviewithflicker.ui.moviedetail

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moham.swvlmoviewithflicker.data.entities.flickrPhoto.Photo
import com.moham.swvlmoviewithflicker.databinding.ItemPictureBinding
import com.moham.swvlmoviewithflicker.utils.loadImage

class MoviePicturesAdapter : RecyclerView.Adapter<PictureViewHolder>() {


    private val pictures = ArrayList<Photo>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Photo>?) {
        this.pictures.clear()
        items?.let { this.pictures.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val binding: ItemPictureBinding =
            ItemPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int = pictures.size

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(pictures[position])
    }
}

class PictureViewHolder(
    private val itemBinding: ItemPictureBinding,
    private val context: Context
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(item: Photo) {
        loadImage(context,item,itemBinding.image){
            itemBinding.progressBar.visibility = GONE
            itemBinding.image.visibility = VISIBLE
        }
    }


}

