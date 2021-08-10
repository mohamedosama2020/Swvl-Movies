package com.moham.swvlmoviewithflicker.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.data.entities.movies.MoviesDetail
import com.google.gson.Gson
import com.moham.swvlmoviewithflicker.R
import com.moham.swvlmoviewithflicker.data.entities.movies.Movie
import com.moham.swvlmoviewithflicker.databinding.MoviesFragmentBinding
import com.moham.swvlmoviewithflicker.utils.autoCleared
import com.moham.swvlmoviewithflicker.utils.loadJSONFromAsset
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesFragment : Fragment(), MoviesAdapter.MovieItemListener {

    private var binding: MoviesFragmentBinding by autoCleared()
    private val viewModel: MoviesViewModel by activityViewModels()
    private lateinit var adapter: MoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadLocalData()
        setUpSearchResult()

    }

    private fun setupRecyclerView() {
        adapter = MoviesAdapter(this)
        binding.moviesRv.layoutManager = LinearLayoutManager(requireContext())
        binding.moviesRv.adapter = adapter
    }

    private fun setUpSearchResult() {
        viewModel.searchQuery.observe(viewLifecycleOwner, {
            search(it)
        })
    }

    private fun loadLocalData() {
        val data = viewModel.loadMoviesList()
        adapter.setItems(data.movies)
    }

    override fun onClickedMovie(movie: Movie) {
        findNavController().navigate(
            R.id.action_moviesFragment_to_movieDetailFragment,
            bundleOf("selected_movie" to movie)
        )
    }

    private fun search(s: String?) {
        adapter.search(s) {
            Toast.makeText(context, "Nothing Found", Toast.LENGTH_SHORT)
            .show()
        }
    }
}
