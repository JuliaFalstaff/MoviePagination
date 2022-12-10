package com.example.moviepagination.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.moviepagination.R
import com.example.moviepagination.databinding.FragmentMovieInfoBinding
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.presentation.adapters.ActorsListAdapter
import com.example.moviepagination.presentation.core.BaseFragment
import com.example.moviepagination.presentation.glide.GlideFactory
import com.example.moviepagination.presentation.viewmodel.MovieInfoViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import org.koin.androidx.scope.createScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class MovieInfoFragment :
    BaseFragment<FragmentMovieInfoBinding>(FragmentMovieInfoBinding::inflate) {

    override val scope: Scope by lazy { createScope(this) }
    private var isFavourite = false
    val viewModel: MovieInfoViewModel by inject()
    private val args by navArgs<MovieInfoFragmentArgs>()
    private lateinit var movieBundle: String
    private var adapter: ActorsListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ActorsListAdapter()
        binding.recyclerViewActors.adapter = adapter
        movieBundle = args.itemId.toString()
        setObservers()
        setRVListeners()
    }

    private fun setObservers() {
        viewModel.checkIsFavouriteAndLoad(movieBundle)
        viewModel.liveDataIsFav.observe(viewLifecycleOwner) {
            isFavourite = it
            setFavButton(it)
            Log.d("MOVIE-INFO IMAGE", it.toString())
        }

        viewModel.loadMovieLiveData.observe(viewLifecycleOwner) {
            renderData(it)
            Log.d("MOVIE-INFO", it.toString())
        }
        viewModel.loadTrailerLiveData.observe(viewLifecycleOwner) { renderTrailerData(it) }
        viewModel.loadMovieTrailer(movieBundle)
    }

    private fun renderTrailerData(appState: AppState?) {
        when (appState) {
            is AppState.SuccessTrailer -> {
                setTrailer(appState.trailerMovie)
            }
            is AppState.Error -> {
                showError(appState.error)
            }
        }
    }

    private fun setRVListeners() {
        adapter?.onItemClickListener = { actor ->
            findNavController().navigate(
                MovieInfoFragmentDirections.actionMovieInfoFragmentToActorInfoFragment(actor.id)
            )
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessMovieInfo -> {
                setData(appState.dataMovie)
                appState.dataMovie.actorList?.let { adapter?.submitList(it) }
                showVisibilityOfMovieInfo()
                binding.movieInfoProgressBar.visibility = View.INVISIBLE
            }
            is AppState.Error -> {
                showError(appState.error)
                hideVisibilityOfMovieInfo()
                binding.movieInfoProgressBar.visibility = View.INVISIBLE
            }
            is AppState.Loading -> {
                binding.movieInfoProgressBar.visibility = View.VISIBLE
                hideVisibilityOfMovieInfo()
            }
        }
    }

    private fun hideVisibilityOfMovieInfo() = with(binding) {
        dateOfReleaseInfoLabelTextView.visibility = View.INVISIBLE
        movieGenresLabelTextView.visibility = View.INVISIBLE
        movieDirectorLabelTextView.visibility = View.INVISIBLE
        movieRunTimeLabelTextView.visibility = View.INVISIBLE
        youtubeVideoTrailer.visibility = View.INVISIBLE
        movieRatingTextView.visibility = View.INVISIBLE
    }

    private fun showVisibilityOfMovieInfo() = with(binding) {
        dateOfReleaseInfoLabelTextView.visibility = View.VISIBLE
        movieGenresLabelTextView.visibility = View.VISIBLE
        movieDirectorLabelTextView.visibility = View.VISIBLE
        movieRunTimeLabelTextView.visibility = View.VISIBLE
        youtubeVideoTrailer.visibility = View.VISIBLE
        movieRatingTextView.visibility = View.VISIBLE
    }

    private fun setData(movie: MovieInfo) {
        with(binding) {
            titleInfoTextView.text = movie.title
            dateOfReleaseInfoTextView.text = movie.year
            movieGenresTextView.text = movie.genres
            movieOverviewTextView.text = movie.plot
            movieDirectorTextView.text = movie.directors
            movieRunTimeTextView.text = movie.runtimeMins
            movieRatingTextView.text = movie.imDbRating
            setFavButton(isFavourite)
            saveToMyListImageButton.setOnClickListener {
                if (!isFavourite) {
                    viewModel.saveMovieToMyList(movie.copy(isFavourite = !movie.isFavourite))
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.success_saved),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.deleteMovieFromMyList(movie)
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.success_deleted),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                setFavButton(!isFavourite)
            }
            GlideFactory.loadPicture(requireView(), movie.image, smallMoviePosterImageView)
        }
    }

    private fun setFavButton(isFav: Boolean) {
        if (isFav) {
            binding.saveToMyListImageButton.setImageResource(R.drawable.ic_added_to_my_list)
        } else {
            binding.saveToMyListImageButton.setImageResource(R.drawable.ic_add_to_list)
        }
    }

    private fun setTrailer(videoId: String) = with(binding) {
        lifecycle.addObserver(youtubeVideoTrailer)
        youtubeVideoTrailer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)
                youTubePlayer.pause()
                Log.d("TAG", "Success: $videoId")
            }
        })
    }
}