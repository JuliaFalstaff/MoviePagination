package com.example.moviepagination.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviepagination.R
import com.example.moviepagination.databinding.FragmentActorInfoBinding
import com.example.moviepagination.domain.AppState
import com.example.moviepagination.domain.entities.castInfo.ActorInfo
import com.example.moviepagination.presentation.adapters.CastMoviesListAdapter
import com.example.moviepagination.presentation.adapters.KnownForMoviesListAdapter
import com.example.moviepagination.presentation.core.BaseFragment
import com.example.moviepagination.presentation.viewmodel.ActorInfoViewModel
import org.koin.androidx.scope.createScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope

class ActorInfoFragment :
    BaseFragment<FragmentActorInfoBinding>(FragmentActorInfoBinding::inflate) {

    override val scope: Scope get() = createScope(this)
    val viewModel: ActorInfoViewModel by inject()
    private val args by navArgs<ActorInfoFragmentArgs>()
    private lateinit var actorBundle: String
    private var adapterCast: CastMoviesListAdapter? = null
    private var adapterKnownFor: KnownForMoviesListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViews()
        actorBundle = args.actorId.toString()
        setObserver()
        setRVListeners()
    }

    private fun setObserver() {
        viewModel.actorLiveData.observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.loadActorInfoById(actorBundle)
    }

    private fun setRecyclerViews() {
        adapterCast = CastMoviesListAdapter()
        adapterKnownFor = KnownForMoviesListAdapter()
        binding.actorCastMovieRecyclerView.adapter = adapterCast
        binding.actorKnownForRecyclerView.adapter = adapterKnownFor
    }

    private fun setRVListeners() {
        adapterCast?.onItemCastClickListener = { movie ->
            findNavController().navigate(
                ActorInfoFragmentDirections
                    .actionActorInfoFragmentToMovieInfoFragment(movie.id)
            )
        }

        adapterKnownFor?.onItemKnownForClickListener = { movie ->
            findNavController().navigate(
                ActorInfoFragmentDirections
                    .actionActorInfoFragmentToMovieInfoFragment(movie.id)
            )
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessActorInfo -> {
                setActorInfoData(appState.actorInfo)
                adapterCast?.submitList(appState.actorInfo.castMovies)
                adapterKnownFor?.submitList(appState.actorInfo.knownFor)
                showVisibilityOfActorInfo()
                binding.retryButton.visibility = View.GONE
            }
            is AppState.Error -> {
                showError(appState.error)
                binding.actorInfoProgressBar.visibility = View.INVISIBLE
            }
            is AppState.Loading -> {
                hideVisibilityOfActorInfo()
            }
        }
    }

    private fun showVisibilityOfActorInfo() = with(binding) {
        actorBirthDateLabelTextView.visibility = View.VISIBLE
        actorHeightLabelTextView.visibility = View.VISIBLE
        binding.actorInfoProgressBar.visibility = View.INVISIBLE
    }

    private fun hideVisibilityOfActorInfo() = with(binding) {
        actorBirthDateLabelTextView.visibility = View.INVISIBLE
        actorHeightLabelTextView.visibility = View.INVISIBLE
        binding.actorInfoProgressBar.visibility = View.VISIBLE
    }

    private fun setActorInfoData(actor: ActorInfo) {
        with(binding) {
            actorNameTextView.text = actor.name
            actorProfessionTextView.text = actor.role
            actorHeightTextView.text = actor.height
            actorBirthDatTextView.text = actor.birthDate
            actorBioTextView.text = actor.summary
            actorsAwardsTextView.text = actor.awards
            Log.d("Actor TAG", "${actor.awards}")
            Glide.with(requireContext())
                .load(actor.image)
                .error(R.drawable.ic_load_error_vector)
                .into(actorPhotoImageView)
        }
    }

    override fun showErrorConnection() = with(binding) {
        if (!isNetworkAvailable) {
            retryButton.visibility = View.VISIBLE
            retryButton.setOnClickListener {
                viewModel.loadActorInfoById(actorBundle)
                Log.d("retry", "click")
            }
        } else {
            retryButton.visibility = View.GONE
        }
    }
}