package com.example.moviepagination.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviepagination.R
import com.example.moviepagination.databinding.FragmentMovieInfoBinding
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.presentation.adapters.ActorsListAdapter
import com.example.moviepagination.presentation.viewmodel.MovieInfoViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class MovieInfoFragment : Fragment(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }
    private var _binding: FragmentMovieInfoBinding? = null
    private val binding get() = _binding!!
    private var isFavourite = false
    val vm: MovieInfoViewModel by inject()
    private val args by navArgs<MovieInfoFragmentArgs>()
    private lateinit var movieBundle: String
    private var adapter: ActorsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ActorsListAdapter()
        binding.recyclerViewActors.adapter = adapter
        movieBundle = args.itemId.toString()
        setObservers()
        setRVListeners()
    }

    private fun setObservers() {
        vm.loadMovieLiveData.observe(viewLifecycleOwner) {
            renderData(it)
            Log.d("MOVIE-INFO", it.toString())
        }
        vm.liveDataIsFav.observe(viewLifecycleOwner) {
            isFavourite = it
            setFavButton(it)
            Log.d("MOVIE-INFO IMAGE", it.toString())
        }
        vm.loadMovieById(movieBundle)
        vm.checkIsFavourite(movieBundle)
        vm.loadTrailerLiveData.observe(viewLifecycleOwner) { setTrailer(it) }
        vm.loadMovieTrailer(movieBundle)
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
                binding.movieInfoProgressBar.visibility = View.INVISIBLE
            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Error Trailer: ${appState.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
                binding.movieInfoProgressBar.visibility = View.INVISIBLE
            }
            is AppState.Loading -> {
                binding.movieInfoProgressBar.visibility = View.VISIBLE
            }
        }
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
                    vm.saveMovieToMyList(movie.copy(isFavourite = !movie.isFavourite))
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.success_saved),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    vm.deleteMovieFromMyList(movie)
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.success_deleted),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                setFavButton(!isFavourite)
            }

            Glide.with(requireContext())
                .load(movie.image)
                .error(R.drawable.ic_load_error_vector)
                .into(smallMoviePosterImageView)
        }
    }

    private fun setFavButton(isFav: Boolean) {
        isFavourite = isFav
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