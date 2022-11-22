package com.example.moviepagination.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.moviepagination.R
import com.example.moviepagination.databinding.FragmentMovieInfoBinding
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.info.Actor
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.presentation.adapters.ActorsListAdapter
import com.example.moviepagination.presentation.adapters.IOnActorClickListener
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
    val vm: MovieInfoViewModel by inject()
    private lateinit var movieBundle: String
    private var adapter: ActorsListAdapter? = null
    private val onListItemClickListener: IOnActorClickListener = object : IOnActorClickListener {
        override fun onActorItemClick(actor: Actor) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                        .replace(R.id.container, ActorInfoFragment.newInstance(Bundle().apply {
                            putString(ActorInfoFragment.ACTOR_INFO, actor.id)
                        }))
                        .addToBackStack("")
                        .commitAllowingStateLoss()
            }
        }
    }

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
        movieBundle = arguments?.getString(MOVIE_INFO).toString()
        vm.getDetailedLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
            Log.d("MOVIE-INFO", it.toString())
        })
        vm.loadMovieById(movieBundle)
        vm.getTrailerLiveData().observe(viewLifecycleOwner, Observer { renderTrailer(it) })
        vm.loadMovieTrailer(movieBundle)
    }

    private fun renderTrailer(appState: AppState) {
        when (appState) {
            is AppState.SuccessTrailer -> {
                appState.trailerMovie.videoId?.let { setTrailer(it) }
                Log.d("TAG", "Success: ${appState.trailerMovie.videoId}")

            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Error: ${appState.error.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("TAG", "Error: ${appState.error.message}")
            }
        }

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessMovieInfo -> {
                setData(appState.dataMovie)
                binding.recyclerViewActors.adapter = appState.dataMovie.actorList?.let {
                    ActorsListAdapter(
                        it, onListItemClickListener
                    )
                }
                appState.dataMovie.actorList?.let { adapter?.setActorsData(it) }
            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "Error Trailer: ${appState.error.message}",
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

    private fun setTrailer(videoId: String) = with(binding) {
        lifecycle.addObserver(youtubeVideoTrailer)
        youtubeVideoTrailer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)
                Log.d("TAG", "Success: $videoId")
            }
        })
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