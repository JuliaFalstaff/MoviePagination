package com.example.moviepagination.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviepagination.R
import com.example.moviepagination.databinding.FragmentActorInfoBinding
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.castInfo.ActorInfo
import com.example.moviepagination.presentation.adapters.CastMoviesListAdapter
import com.example.moviepagination.presentation.adapters.KnownForMoviesListAdapter
import com.example.moviepagination.presentation.viewmodel.ActorInfoViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class ActorInfoFragment : Fragment(), KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }
    private var _binding: FragmentActorInfoBinding? = null
    private val binding get() = _binding!!
    val viewModel: ActorInfoViewModel by inject()
    private val args by navArgs<ActorInfoFragmentArgs>()
    private lateinit var actorBundle: String
    private var adapterCast: CastMoviesListAdapter? = null
    private var adapterKnownFor: KnownForMoviesListAdapter? = null

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
        adapterCast = CastMoviesListAdapter()
        adapterKnownFor = KnownForMoviesListAdapter()
        binding.actorCastMovieRecyclerView.adapter = adapterCast
        binding.actorKnownForRecyclerView.adapter = adapterKnownFor
        actorBundle = args.actorId.toString()
        viewModel.getActorInfoLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.loadActorInfoById(actorBundle)
        setRVListeners()
    }

    private fun setRVListeners() {
        adapterCast?.onItemCastClickListener = { movie ->
//            activity?.supportFragmentManager?.apply {
//                beginTransaction()
//                    .replace(R.id.container, MovieInfoFragment.newInstance(Bundle().apply {
//                        putString(MovieInfoFragment.MOVIE_INFO, movie.id)
//                    }))
//                    .addToBackStack("")
//                    .commitAllowingStateLoss()
//            }
            ActorInfoFragmentDirections.actionActorInfoFragmentToMovieInfoFragment(movie.id)
        }

        adapterKnownFor?.onItemKnownForClickListener = { movie ->
//            activity?.supportFragmentManager?.apply {
//                beginTransaction()
//                    .replace(R.id.container, MovieInfoFragment.newInstance(Bundle().apply {
//                        putString(MovieInfoFragment.MOVIE_INFO, movie.id)
//                    }))
//                    .addToBackStack("")
//                    .commitAllowingStateLoss()
//            }
            findNavController().navigate(
                ActorInfoFragmentDirections
                    .actionActorInfoFragmentToMovieInfoFragment(movie.id)
            )
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessActorInfo -> {
                setData(appState.actorInfo)
                adapterCast?.submitList(appState.actorInfo.castMovies)
                adapterKnownFor?.submitList(appState.actorInfo.knownFor)
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
}