package com.example.moviepagination.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviepagination.R
import com.example.moviepagination.databinding.FragmentMovieInfoBinding
import com.example.moviepagination.model.AppState
import com.example.moviepagination.model.data.Item
import com.example.moviepagination.model.data.info.MovieInfo
import com.example.moviepagination.ui.adapters.ActorsListAdapter
import com.example.moviepagination.viewmodel.MovieInfoViewModel

class MovieInfoFragment : Fragment() {

    private var _binding: FragmentMovieInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var vm: MovieInfoViewModel
    private lateinit var movieBundle: Item
    private var adapter: ActorsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewActors.adapter = adapter
        movieBundle = arguments?.getParcelable(MOVIE_INFO) ?: Item("")
        vm = ViewModelProvider(this).get(MovieInfoViewModel::class.java)
        vm.getDetailedLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
            Log.d("MOVIE-INFO", it.toString())
        })
        vm.loadMovieById(movieBundle.id)

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessMovieInfo -> {
                setData(appState.dataMovie)
                binding.recyclerViewActors.adapter = appState.dataMovie.actorList?.let {
                    ActorsListAdapter(
                        it
                    )
                }
                appState.dataMovie.actorList?.let { adapter?.setActorsData(it) }
            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Error: ${appState.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setData(movie: MovieInfo) {
        with(binding) {
            titleInfoTextView.text = movie.title
            dateOfReleaseInfoTextView.text = movie.year
            movieGenresTextView.text = movie.genres
            movieOverviewTextView.text = movie.plot

            Glide.with(requireContext())
                .load(movie.image)
                .error(R.drawable.ic_load_error_vector)
                .into(smallMoviePosterImageView)

            Glide.with(requireContext())
                .load(movie.image)
                .error(R.drawable.ic_load_error_vector)
                .into(backgroundPosterImageView)
        }
    }

    companion object {
        const val MOVIE_INFO = "Movie"
        fun newInstance(bundle: Bundle): MovieInfoFragment {
            val fragment = MovieInfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}