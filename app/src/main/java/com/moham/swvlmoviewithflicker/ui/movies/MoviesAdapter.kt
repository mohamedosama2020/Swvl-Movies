package com.moham.swvlmoviewithflicker.ui.movies

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moham.swvlmoviewithflicker.R
import com.moham.swvlmoviewithflicker.data.entities.movies.Movie
import com.moham.swvlmoviewithflicker.databinding.ItemMovieBinding
import com.moham.swvlmoviewithflicker.databinding.MovieHeaderBinding

class MoviesAdapter(private val listener: MovieItemListener) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private val moviesList = ArrayList<Movie>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Movie>?) {
        this.moviesList.clear()
        items?.let { this.moviesList.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: ItemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, parent.context)
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
    private val context: Context
) : RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var movie: Movie

    fun bind(item: Movie) {
        this.movie = item
        itemBinding.title.text = item.title
        itemBinding.ratingAndYear.text =
            context.getString(R.string.rating_year, item.rating, item.year)
    }
}

class HeaderViewHolder(
    private val itemBinding: MovieHeaderBinding,
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(year:String) {
        itemBinding.tvTitle.text = year
    }
}


