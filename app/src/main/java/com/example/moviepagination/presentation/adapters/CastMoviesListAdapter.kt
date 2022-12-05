package com.example.moviepagination.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepagination.databinding.ItemActorCastMovieRecyclerViewBinding
import com.example.moviepagination.domain.entities.castInfo.CastMovie
import com.example.moviepagination.presentation.core.BaseItemCallback

class CastMoviesListAdapter :
    ListAdapter<CastMovie, CastMoviesListAdapter.CastMoviesViewHolder>(BaseItemCallback<CastMovie>()) {

    var onItemCastClickListener: ((CastMovie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastMoviesViewHolder {
        return CastMoviesViewHolder(
            ItemActorCastMovieRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CastMoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CastMoviesViewHolder(val binding: ItemActorCastMovieRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: CastMovie) = with(binding) {
            castMovieTitleTextView.text = movie.title
            castMovieRoleTextView.text = movie.role
            castMovieYearTextView.text = movie.year
            itemView.setOnClickListener {
                onItemCastClickListener?.invoke(movie)
            }
        }
    }
}