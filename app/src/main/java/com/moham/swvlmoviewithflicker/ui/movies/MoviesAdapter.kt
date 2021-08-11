package com.moham.swvlmoviewithflicker.ui.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moham.swvlmoviewithflicker.data.entities.movies.Movie
import com.moham.swvlmoviewithflicker.databinding.ItemMovieBinding
import com.moham.swvlmoviewithflicker.databinding.MovieHeaderBinding

class MoviesAdapter(private val listener: MovieItemListener) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private val moviesList = ArrayList<Movie>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Movie>) {
        moviesList.clear()
        moviesList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: ItemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesList[position])

        holder.itemView.setOnClickListener {
            listener.onClickedMovie(moviesList[position])
        }
    }

    fun getOriginalMoviesList(): List<Movie> {
        return moviesList
    }

    interface MovieItemListener {
        fun onClickedMovie(movie: Movie)
    }

}

class MovieViewHolder(
    private val itemBinding: ItemMovieBinding,
) : RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var movie: Movie

    fun bind(item: Movie) {
        this.movie = item
        itemBinding.title.text = item.title
        itemBinding.rating.rating = item.rating.toFloat()
        itemBinding.tvYearValue.text = item.year.toString()
    }
}

class HeaderViewHolder(
    private val itemBinding: MovieHeaderBinding,
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(year: String) {
        itemBinding.tvTitle.text = year
    }
}


