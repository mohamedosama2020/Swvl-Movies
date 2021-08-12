package com.moham.swvlmoviewithflicker.ui.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.moham.swvlmoviewithflicker.R
import com.moham.swvlmoviewithflicker.data.entities.flickrPhoto.FlickrPhoto
import com.moham.swvlmoviewithflicker.data.entities.flickrPhoto.Photo
import com.moham.swvlmoviewithflicker.data.entities.movies.Movie
import com.moham.swvlmoviewithflicker.databinding.MovieDetailFragmentBinding
import com.moham.swvlmoviewithflicker.utils.Resource
import com.moham.swvlmoviewithflicker.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var binding: MovieDetailFragmentBinding by autoCleared()
    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(false)
        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie =
            arguments?.getParcelable<Movie>("selected_movie")
        movie?.let {
            bindMovieDetails(movie)
            setupObservers(it)
        }
    }

    private fun setupObservers(movie: Movie) {
        viewModel.getPhotos(movie.title)
        viewModel.movieLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    handleSuccessResponse(it)
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(activity, it.message, LENGTH_LONG).show()
                    binding.progressBar.visibility = GONE
                }
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = VISIBLE
                }
            }
        })
    }

    private fun handleSuccessResponse(resource: Resource<FlickrPhoto>) {
        checkIfNoPhotos(resource)
        binding.progressBar.visibility = GONE
    }

    private fun checkIfNoPhotos(resource: Resource<FlickrPhoto>) {
        if (resource.data?.photos?.photo?.size!! > 0) {
            setupImageRecyclerView(resource.data.photos.photo)
        } else {
            Toast.makeText(context, "No Picture found on Flickr", LENGTH_LONG).show()
        }
    }

    private fun bindMovieDetails(movie: Movie) {

        binding.title.text = getString(R.string.movie_name, movie.title)
        binding.rating.rating = movie.rating.toFloat()
        binding.tvYearValue.text = movie.year.toString()

        if (movie.genres.isNotEmpty()) {
            binding.genresHeading.visibility = VISIBLE
            binding.genres.visibility = VISIBLE
            movie.genres.forEach {
                binding.genres.append(it.plus("\n"))
            }
        }

        if (movie.cast.isNotEmpty()) {
            binding.castHeading.visibility = VISIBLE
            binding.casts.visibility = VISIBLE
            movie.cast.forEach {
                binding.casts.append(it.plus("\n"))
            }
        }
    }

    private fun setupImageRecyclerView(photos: List<Photo>?) {
        val adapter = MoviePicturesAdapter()
        binding.moviePictures.layoutManager = GridLayoutManager(context, 2)
        binding.moviePictures.adapter = adapter
        adapter.setItems(photos)
    }

}
