package com.moham.swvlmoviewithflicker.ui.movies

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.moham.swvlmoviewithflicker.R
import com.moham.swvlmoviewithflicker.data.entities.movies.GroupedMovies
import com.moham.swvlmoviewithflicker.databinding.ItemMovieBinding
import com.moham.swvlmoviewithflicker.databinding.MovieHeaderBinding
import io.github.luizgrp.sectionedrecyclerviewadapter.Section
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters

class MovieSection(
    private val groupedMovies: GroupedMovies,
    private val listener: MoviesAdapter.MovieItemListener
) : Section(
    SectionParameters.builder()
        .itemResourceId(R.layout.item_movie)
        .headerResourceId(R.layout.movie_header)
        .build()
) {

    override fun getContentItemsTotal(): Int {
        return groupedMovies.movies.size
    }

    override fun getItemViewHolder(view: View?): RecyclerView.ViewHolder {
        val binding: ItemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(view?.context))
        return MovieViewHolder(binding, view?.context!!)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val itemHolder = (holder as MovieViewHolder)
        itemHolder.bind(groupedMovies.movies[position])
        itemHolder.itemView.setOnClickListener {
            listener.onClickedMovie(groupedMovies.movies[position])
        }
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
        (holder as HeaderViewHolder).bind(groupedMovies.year.toString())
    }

    override fun getHeaderViewHolder(view: View?): RecyclerView.ViewHolder {
        val binding: MovieHeaderBinding =
            MovieHeaderBinding.inflate(LayoutInflater.from(view?.context))
        return HeaderViewHolder(binding)
    }
}