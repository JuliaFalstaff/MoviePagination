package com.example.moviepagination.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.moviepagination.R
import com.example.moviepagination.databinding.FragmentActorInfoBinding
import com.example.moviepagination.model.AppState
import com.example.moviepagination.model.data.castInfo.ActorInfo
import com.example.moviepagination.model.data.castInfo.CastMovie
import com.example.moviepagination.model.data.castInfo.KnownFor
import com.example.moviepagination.ui.adapters.CastMoviesListAdapter
import com.example.moviepagination.ui.adapters.IOnMovieItemClickListener
import com.example.moviepagination.ui.adapters.KnownForMoviesListAdapter
import com.example.moviepagination.viewmodel.ActorInfoViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class ActorInfoFragment : Fragment(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }
    private var _binding: FragmentActorInfoBinding? = null
    private val binding get() = _binding!!
    val viewModel: ActorInfoViewModel by inject()
    private lateinit var actorBundle: String
    private var adapter: CastMoviesListAdapter? = null
    private var adapterKnownFor: KnownForMoviesListAdapter? = null
    private val onListItemClickListener: IOnMovieItemClickListener =
            object : IOnMovieItemClickListener {
                override fun onItemClick(movie: CastMovie) {
                    activity?.supportFragmentManager?.apply {
                        beginTransaction()
                                .replace(R.id.container, MovieInfoFragment.newInstance(Bundle().apply {
                                    putString(MovieInfoFragment.MOVIE_INFO, movie.id)
                                }))
                                .addToBackStack("")
                                .commitAllowingStateLoss()
                    }
                }

                override fun onItemKnownClick(movie: KnownFor) {
                    activity?.supportFragmentManager?.apply {
                        beginTransaction()
                                .replace(R.id.container, MovieInfoFragment.newInstance(Bundle().apply {
                                    putString(MovieInfoFragment.MOVIE_INFO, movie.id)
                                }))
                                .addToBackStack("")
                                .commitAllowingStateLoss()
                    }
                }
            }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentActorInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { actorBundle = it.getString(ACTOR_INFO).toString() }
        viewModel.getActorInfoLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.loadActorInfoById(actorBundle)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessActorInfo -> {
                setData(appState.actorInfo)
                binding.actorCastMovieRecyclerView.adapter = appState.actorInfo.castMovies?.let {
                    CastMoviesListAdapter(
                            it, onListItemClickListener
                    )
                }
                appState.actorInfo.castMovies?.let { adapter?.setMovieData(it) }

                binding.actorKnownForRecyclerView.adapter = appState.actorInfo.knownFor?.let {
                    KnownForMoviesListAdapter(
                            it, onListItemClickListener
                    )
                }
                appState.actorInfo.knownFor?.let { adapterKnownFor?.setKnownMovieData(it) }
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

    private fun setData(actor: ActorInfo) {
        with(binding) {
            actorNameTextView.text = actor.name
            actorProfessionTextView.text = actor.role
            actorHeightTextView.text = actor.height
            actorBirthDatTextView.text = actor.birthDate
            actorBioTextView.text = actor.summary
            actorsAwardsTextView.text = actor.awards
            Glide.with(requireContext())
                    .load(actor.image)
                    .error(R.drawable.ic_load_error_vector)
                    .into(actorPhotoImageView)
        }
    }

    companion object {
        const val ACTOR_INFO = "Actor"
        fun newInstance(bundle: Bundle): ActorInfoFragment {
            val fragment = ActorInfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}